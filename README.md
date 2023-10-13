# saga-pattern-choreography
Saga Pattern Choreography in Microservice Using Spring Boot

### How to implement SAGA design pattern in Spring Boot?

Contents:

1. Problem
2. Solution
3. Implementation
4. Code
5. Testing
6. Advantages
7. Disadvantages
8. Conclusion

### Problem:

Microservices come with their own advantages and disadvantages.

One such disadvantage is managing distributed transactions.

Let’s say your transaction spans 4 different microservices.

How do you ensure that your transaction either commits successfully with all the 4 tasks succeeding or fails
successfully if any of the tasks is not completed ( the completed ones are rolled back)?

Spring Boot provides the annotation @Transactional to manage transactions.

But this works only within a single method and within a single project.

### Solution:

There is a design pattern which solves the issue with distributed transactions in microservices.

It was originally proposed by the computer scientist Hector Gracia Molina and Kenneth Salem as mentioned in their
paper [here](https://www.cs.cornell.edu/andru/cs711/2002fa/reading/sagas.pdf).

As suggested in the paper , they created this for Long Lived Transactions (LLT) and not for microservices but it serves
well for microservices.

A long lived transaction is a transaction which takes a longer time , may be minutes , hours or even days. You can’t
lock a database until all the tasks in such transaction completes for it will severely affect the performance of the
application. Hence they came up with the design pattern SAGA (probably named SAGA because they created it for dealing
with long transactions – SAGA means a very long story).

It goes like this :

If your transaction contains , lets say 4 tasks,

You create a **compensating task** for each task except the last.

So that if any task fails then you run the compensating tasks of the previous tasks to rollback the original effect.

So if there are four tasks T1, T2 , T3 and T4,

Then you have three corresponding compensating tasks C1,C2,C3.

If , for example , T1 and T2 succeeds and T3 fails , then you run C2 and C1 to rollback the effects of T1 and T2 in *
*Last In First Out order**.

So the sequence of the transaction goes like this : T1 -> T2 -> T3 (failed) -> C2 -> C1.

You don’t need a compensating task for the last task because if the last task fails you just need to roll back the
previous tasks.

This is called **Backward Recovery** since you go back and execute the compensating tasks of already completed
successful tasks.

You can also try **Forward Recovery** by retrying T3 and then T4 if your business use case requires it.

Backward Recovery is more common though.

On a high level this is what SAGA design pattern is.

It can be implemented in two ways:

* Choreography
* Orchestration
  Choreography means the tasks execute independently. Once one task is completed , it invokes the next tasks in the
  sequence. In case if the next task fails then it invokes the compensating tasks for the previous tasks.

Orchestration means the tasks are invoked by another parent task.

It plays the role of an orchestrator.

It calls each tasks in sequence and based on their response decides whether to call the next task or the compensating
tasks.

Let us implement SAGA using Choreography in this example.

It is simpler and neat compared to Orchestration.