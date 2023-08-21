package kata;

import com.github.larseckart.tcr.TestCommitRevertMainExtension;
import org.approvaltests.Approvals;
import org.approvaltests.StoryBoard;
import org.approvaltests.reporters.DelayedClipboardReporter;
import org.approvaltests.reporters.DiffReporter;
import org.approvaltests.reporters.UseReporter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(TestCommitRevertMainExtension.class)
@UseReporter({DiffReporter.class, LinuxKdiff3Reporter.class, DelayedClipboardReporter.class})
class MonopolyTest {

    @Test
    void testBasicPlay() {
        StoryBoard story = new StoryBoard();
        Monopoly monopoly = new Monopoly(3);
        story.addFrame(monopoly);
        story.addFrame(monopoly.move(5).buyProperty().endTurn());
        story.addFrame(monopoly.move(3).buyProperty().endTurn());
        story.addFrame(monopoly.move(3).endTurn());
        Approvals.verify(story);
    }

    @Test
    void testIncomeTaxAndChanceAndJail() {
        StoryBoard story = new StoryBoard();
        ChancePile.stack(new ChanceCard.BankCard());
        Monopoly monopoly = new Monopoly(3);
        story.addFrame(monopoly);
        story.addFrame(monopoly.move(10).endTurn());
        story.addFrame(monopoly.move(4).endTurn());
        story.addFrame(monopoly.move(7).endTurn());
        Approvals.verify(story);
    }

    @Test
    void testOtherChances() {
        StoryBoard story = new StoryBoard();
        ChancePile.stack(new ChanceCard.BankCard()
                , new ChanceCard.GoToStCharlesPlace()
                , new ChanceCard.GoBack3Spaces()
                , new ChanceCard.SpeedingFine()
                , new ChanceCard.ChairmanOfTheBoard()
                , new ChanceCard.GetOutOfJailFree()
                , new ChanceCard.AdvanceToNearestRailroad()
        );
        int chanceCards = ChancePile.size();
        Monopoly monopoly = new Monopoly(chanceCards);
        story.addFrame(monopoly);
        for (int i = 0; i < chanceCards; i++) {
            if (i == 1) {
                story.addFrame(monopoly.move(7).buyProperty().endTurn());
            } else {
                story.addFrame(monopoly.move(7).endTurn());
            }
        }

        Approvals.verify(story);
    }

    @Test
    void testPlayerDetails() {
        Player player = new Player("Player 1");

        assertThat(player.details()).isEqualTo("Player 1 ($1500)  ");
    }

    @Test
    void testPlayerLocation() {
        Player player = new Player("Player 1");

        assertThat(player.location()).isEqualTo(0);
    }

    @Test
    void testRollingDoubles() {
        StoryBoard story = new StoryBoard();
        Monopoly monopoly = new Monopoly(2);
        story.addFrame(monopoly);
        story.addFrame(monopoly
                .move(12, true).buyProperty()
                .move(12, true).buyProperty()
                .move(11, false).buyProperty()
                .endTurn());
        Approvals.verify(story);
    }


    // write a test to pass go
    // write a test for rolling three doubles in a row
    // write a test for trying to go without rolling doubles
}
