//  Copyright (c) 2024 Mastercard, LLC (http://www.mastercard.com/)

// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// THE SOFTWARE.


package com.mc.folderteardown;

//import com.gargoylesoftware.htmlunit.html.HtmlForm;
import hudson.model.ParameterValue;
import hudson.model.ParametersAction;
import jenkins.branch.BranchProperty;
import jenkins.branch.BranchSource;
import jenkins.branch.DefaultBranchPropertyStrategy;
import jenkins.plugins.git.GitSCMSource;
import jenkins.plugins.git.GitSampleRepoRule;
import org.jenkinsci.plugins.workflow.cps.CpsFlowDefinition;
import org.jenkinsci.plugins.workflow.job.WorkflowJob;
import org.jenkinsci.plugins.workflow.job.WorkflowRun;
import org.jenkinsci.plugins.workflow.libs.GlobalLibraries;
import org.jenkinsci.plugins.workflow.libs.LibraryConfiguration;
import org.jenkinsci.plugins.workflow.libs.SCMSourceRetriever;
import org.jenkinsci.plugins.workflow.multibranch.WorkflowMultiBranchProject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.fail;

public class JobTearDownListenerTest {

    @Rule public JenkinsRule j = new JenkinsRule();
    @Rule public GitSampleRepoRule sampleRepo = new GitSampleRepoRule();

    private static final String DEFAULT_JOB = "job-tear-down-executor";
    private static final String PIPELINE_JOB = "my-custom-pipeline-executor";
    private static final String GLOBAL_JOB = "my-custom-global-executor";

    @Before
    public void setUp() throws Exception {
        sampleRepo.init();
        sampleRepo.write("Jenkinsfile", "echo \"branch=${env.BRANCH_NAME}\"; node {checkout scm; echo readFile('file')}");
        sampleRepo.write("file", "initial content");
        sampleRepo.git("add", "Jenkinsfile");
        sampleRepo.git("commit", "--all", "--message=flow");

        sampleRepo.git("checkout", "-b", "feature");
        sampleRepo.write("Jenkinsfile", "echo \"branch=${env.BRANCH_NAME}\"; node {checkout scm; echo readFile('file').toUpperCase()}");
        sampleRepo.write("file", "subsequent content");
        sampleRepo.git("commit", "--all", "--message=tweaked");
    }

    @Test
    public void defaultJobConfiguredAndTriggered() throws Exception {
//        WorkflowJob tearDownJob = j.jenkins.createProject(WorkflowJob.class, DEFAULT_JOB);
//        WorkflowJob p = createBasicJob();
//
//        Assert.assertEquals(tearDownJob.getNextBuildNumber(), 1);
//        j.assertBuildStatusSuccess(p.scheduleBuild2(0).get());
//        p.delete();
//        Assert.assertEquals(tearDownJob.getNextBuildNumber(), 2);
//        verifyParameters(tearDownJob);
    }

    @Test
    public void userDefinedJobTriggered() throws Exception {
//        WorkflowJob tearDownJob = j.jenkins.createProject(WorkflowJob.class, DEFAULT_JOB);
//        WorkflowJob customJob = j.jenkins.createProject(WorkflowJob.class, GLOBAL_JOB);
//        WorkflowJob p = createBasicJob();
//        setConfigSettings();
//
//        Assert.assertEquals(tearDownJob.getNextBuildNumber(), 1);
//        Assert.assertEquals(customJob.getNextBuildNumber(), 1);
//        j.assertBuildStatusSuccess(p.scheduleBuild2(0).get());
//        p.delete();
//        Assert.assertEquals(tearDownJob.getNextBuildNumber(), 1);
//        Assert.assertEquals(customJob.getNextBuildNumber(), 2);
//        verifyParameters(customJob);
    }

    @Test
    public void pipelineDefinedJobTriggered() throws Exception {
//        WorkflowJob tearDownJob = j.jenkins.createProject(WorkflowJob.class, DEFAULT_JOB);
//        WorkflowJob customJob = j.jenkins.createProject(WorkflowJob.class, PIPELINE_JOB);
//        WorkflowJob p = createCustomJob();
//
//        Assert.assertEquals(tearDownJob.getNextBuildNumber(), 1);
//        Assert.assertEquals(customJob.getNextBuildNumber(), 1);
//        WorkflowRun run = j.assertBuildStatusSuccess(p.scheduleBuild2(0).get());
//        p.delete();
//        Assert.assertEquals(tearDownJob.getNextBuildNumber(), 1);
//        Assert.assertEquals(customJob.getNextBuildNumber(), 2);
//        verifyParameters(customJob);
    }

    @Test
    public void pipelineDefinedJobTakesPrecedenceOverGlobalSettings() throws Exception {
//        WorkflowJob tearDownJob = j.jenkins.createProject(WorkflowJob.class, DEFAULT_JOB);
//        WorkflowJob customJob = j.jenkins.createProject(WorkflowJob.class, GLOBAL_JOB);
//        WorkflowJob pipelineJob = j.jenkins.createProject(WorkflowJob.class, PIPELINE_JOB);
//        WorkflowJob p = createCustomJob();
//        setConfigSettings();
//
//        Assert.assertEquals(tearDownJob.getNextBuildNumber(), 1);
//        Assert.assertEquals(customJob.getNextBuildNumber(), 1);
//        Assert.assertEquals(pipelineJob.getNextBuildNumber(), 1);
//        j.assertBuildStatusSuccess(p.scheduleBuild2(0).get());
//        p.delete();
//        Assert.assertEquals(tearDownJob.getNextBuildNumber(), 1);
//        Assert.assertEquals(customJob.getNextBuildNumber(), 1);
//        Assert.assertEquals(pipelineJob.getNextBuildNumber(), 2);
//        verifyParameters(pipelineJob);
    }

    @Test
    public void teardownIgnoredGlobalSharedLibraries() throws Exception {
//        WorkflowJob tearDownJob = j.jenkins.createProject(WorkflowJob.class, DEFAULT_JOB);
//        addGlobalLibrary();
//        WorkflowJob p = createBasicJob();
//
//        Assert.assertEquals(tearDownJob.getNextBuildNumber(), 1);
//        j.assertBuildStatusSuccess(p.scheduleBuild2(0).get());
//        p.delete();
//        Assert.assertEquals(tearDownJob.getNextBuildNumber(), 2);
//        verifyParameters(tearDownJob);
    }

    private WorkflowJob scheduleAndFindBranchProject(WorkflowMultiBranchProject mp, String name) throws Exception {
//        mp.scheduleBuild2(0).getFuture().get();
//        WorkflowJob p = mp.getItem(name);
//        mp.getIndexing();
//        if (p == null) {
//            fail(name + " project not found");
//        }
//        return p;
        return null;
    }

    private WorkflowJob createBasicJob() throws Exception {
//        WorkflowMultiBranchProject mp = j.jenkins.createProject(WorkflowMultiBranchProject.class, "p");
//        mp.getSourcesList().add(new BranchSource(new GitSCMSource(null, sampleRepo.toString(), "", "*", "", false), new DefaultBranchPropertyStrategy(new BranchProperty[0])));
//        WorkflowJob p = scheduleAndFindBranchProject(mp, "feature");
//        p.setDefinition(new CpsFlowDefinition("node { checkout scm }", true));
//        return p;
        return null;
    }

    private WorkflowJob createCustomJob() throws Exception {
//        WorkflowMultiBranchProject mp = j.jenkins.createProject(WorkflowMultiBranchProject.class, "p");
//        mp.getSourcesList().add(new BranchSource(new GitSCMSource(null, sampleRepo.toString(), "", "*", "", false), new DefaultBranchPropertyStrategy(new BranchProperty[0])));
//        WorkflowJob p = scheduleAndFindBranchProject(mp, "feature");
//        p.setDefinition(new CpsFlowDefinition(
//                "properties([branchTearDownExecutor('"+PIPELINE_JOB+"')])\n" +
//                        "node { " +
//                        "checkout scm " +
//                        "}", true));
//        return p;
        return null;
    }

    private void setConfigSettings() throws Exception {
//        HtmlForm form = j.createWebClient().goTo("configure").getFormByName("config");
//        form.getInputByName("jobteardown.tearDownJob").setValueAttribute(GLOBAL_JOB);
//        j.submit(form);
    }

    private void verifyParameters(WorkflowJob job) {
//        WorkflowRun run = job.getLastBuild();
//        Assert.assertEquals(run.number, 1);
//        ParametersAction action = run.getAction(ParametersAction.class);
//        List<ParameterValue> params = action.getAllParameters();
//        Assert.assertEquals(params.size(), 2);
//        Assert.assertEquals(params.get(0).getName(), "git_url");
//        Assert.assertFalse(params.get(0).getValue().toString().isEmpty());
//        Assert.assertNotEquals(params.get(0).getValue(), "https://github.com/fabric8io/jenkins-pipeline-library");
//        Assert.assertEquals(params.get(1).getName(), "branch_name");
//        Assert.assertEquals(params.get(0).getValue(), sampleRepo.getRoot().toString());
//        Assert.assertEquals(params.get(1).getValue(), "feature");
    }

    private void addGlobalLibrary() throws Exception {
//        j.configRoundtrip();
//        GlobalLibraries gl = GlobalLibraries.get();
//        LibraryConfiguration bar = new LibraryConfiguration("bar", new SCMSourceRetriever(new GitSCMSource(null, "https://github.com/fabric8io/jenkins-pipeline-library", "", "origin", "+refs/heads/*:refs/remotes/origin/*", "*", "", true)));
//        bar.setDefaultVersion("master");
//        bar.setImplicit(true);
//        bar.setAllowVersionOverride(false);
//        gl.setLibraries(Arrays.asList(bar));
    }
}
