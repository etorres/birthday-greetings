The Birthday Greetings Kata
=

Originally described in:

http://matteo.vaccari.name/blog/archives/154

Problem
==

Write a program that loads a set of employee records from a flat file, and sends a greetings email to all employees whose birthday is today.

The flat file is a sequence of records, separated by newlines; this are the first few lines:

```text
last_name, first_name, date_of_birth, email
Doe, John, 1982/10/08, john.doe@foobar.com
Ann, Mary, 1975/09/11, mary.ann@foobar.com
```

The greetings email contains the following text:

```text
Subject: Happy birthday!

Happy birthday, dear John!
```

Solution
==

The design of the system emerges from the use of outside-in TDD.

Additional complication
==

The file is stored in an Amazon S3 bucket. An acceptance test uses a copy of the real file, and an integration test uses the S3 file.

The basic steps for configuring the Amazon credentials:

https://github.com/aws/aws-sdk-java/tree/master/src/samples/AmazonS3#running-the-sample
