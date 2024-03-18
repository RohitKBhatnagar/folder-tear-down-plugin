# Folder Tear Down

## Introduction

This plugin is intended to support cleanup of any resources defined within the folder tree, these include cleanup of WorkFlow/Multi-Branch pipelines including credentials defined within the folder tree. 
Currently it is designed to work with Multibranch pipeline projects. When a particular branch is deleted, this plugin will trigger another job with the `git_url` and `branch_name` of the job being deleted. 
This way you can perform any cleanup you desired like bringing down servers.

## Requirements

* Jenkins version 2.426.3+

## Getting started

By default this plugin looks for a job called `job-tear-down-executor`. You may also specify the job to use by going to Manage Jenkins and configuring the Job Name under `Folder Tear Down Plugin`. 
Additionally, you may use `branchTearDownExecutor` in your pipeline to specify a specific job to use for that branch.

Example in pipeline

```
// Scripted Pipelines
properties([branchTearDownExecutor('my-special-job')])
 
// Declarative Pipelines
options {
  branchTearDownExecutor 'my-special-job'
}
```

## Issues

Report issues and enhancements in the [Jenkins issue tracker](https://issues.jenkins-ci.org/).

## Contributing

Refer to our [contribution guidelines](https://github.com/jenkinsci/.github/blob/master/CONTRIBUTING.md)

## LICENSE

Licensed under MIT, see [LICENSE](LICENSE.md)

