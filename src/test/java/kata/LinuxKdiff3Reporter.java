package kata;

import org.approvaltests.core.ApprovalFailureReporter;
import org.approvaltests.reporters.GenericDiffReporter;

public class LinuxKdiff3Reporter extends GenericDiffReporter {
    public LinuxKdiff3Reporter() {
        super("/usr/bin/kdiff3");
    }
}
