package analyzer;

import dataObject.CommitInfo;
import dataObject.RepoInfo;
import git.GitChecker;
import git.GitLogger;

import java.util.ArrayList;

/**
 * Created by matanghao1 on 21/6/17.
 */
public class RepoAnalyzer {


    public static RepoInfo analyzeRecentNCommit(String repoRoot, int recent){
        ArrayList<CommitInfo> commits = GitLogger.getCommits(repoRoot, recent);
        processCommits(repoRoot, commits);
        return new RepoInfo(commits);
    }

    public static RepoInfo analyzeAllCommit(String repoRoot){
        ArrayList<CommitInfo> commits = GitLogger.getCommits(repoRoot);
        processCommits(repoRoot, commits);
        return new RepoInfo(commits);
    }

    private static void processCommits(String repoRoot, ArrayList<CommitInfo> commits){
        for (CommitInfo commitInfo : commits){
            GitChecker.checkOutToCommit(repoRoot,commitInfo);
            CommitAnalyzer.aggregateFileInfos(repoRoot,commitInfo);
        }
        GitChecker.checkOutToRecentBranch(repoRoot);
    }
}