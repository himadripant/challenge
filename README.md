# Kick-starting
* Please put the file to input at `src/main/resources/input/`
* Please pass the absolute path of the file as a command line argument.
    ```shell
    java au.com.seek.aips.challenge.ChallengeApplication "src/main/resources/data.txt"
    ```
  assuming the input file is named `data.txt`
## or
* Launch the test case `au.com.seek.aips.challenge.ChallengeApplicationTests.kickoff`
# Output
The application outputs the results in the console log as:
```
11:13:47.150 [Test worker] INFO  au.com.seek.aips.challenge.ChallengeApplication - The number of cars seen in total: 398
11:13:47.153 [Test worker] INFO  au.com.seek.aips.challenge.ChallengeApplication - The number of cars seen on given dates: 
2021-12-01 179
2021-12-05 81
2021-12-08 134
2021-12-09 4
11:13:47.154 [Test worker] INFO  au.com.seek.aips.challenge.ChallengeApplication - The top 3 half hours with most cars: 
2021-12-01T07:30 46
2021-12-01T08:00 42
2021-12-08T18:00 33
11:13:47.158 [Test worker] INFO  au.com.seek.aips.challenge.ChallengeApplication - The 1.5 hour period with least cars: 
2021-12-01T05:00 2021-12-01T05:30 2021-12-01T06:00
BUILD SUCCESSFUL in 1s
5 actionable tasks: 3 executed, 2 up-to-date
11:13:47 am: Execution finished ':test --tests "au.com.seek.aips.challenge.ChallengeApplicationTests.kickoff"'.
```


