Nevado JMS
==========

This branch has been changed to pass AWSCredentials derived from InstanceProfileCredentialsProvider but this does NOT work
because when uploaded to AWS it produces an Access Denied exception!
Need to investigate why but NOT done yet!

A JMS driver for Amazon Web Services' queue and notification services (SQS/SNS).

Getting started is easy.  Download the jar or add the following to your pom.xml:

    <dependency>
        <groupId>org.skyscreamer</groupId>
        <artifactId>nevado-jms</artifactId>
        <version>1.3.2</version>
    </dependency>

Initializing Spring is a piece of cake.

    <bean id="sqsConnectorFactory" class="org.skyscreamer.nevado.jms.connector.amazonaws.AmazonAwsSQSConnectorFactory" />

    <!-- And this is an implementation of javax.jms.ConnectionFactory -->
    <bean id="connectionFactory" class="org.skyscreamer.nevado.jms.NevadoConnectionFactory">
        <property name="sqsConnectorFactory" ref="sqsConnectorFactory" />
        <property name="awsAccessKey" value="${aws.accessKey}" /> <!-- Set this -->
        <property name="awsSecretKey" value="${aws.secretKey}" /> <!-- And this -->
    </bean>

And now you've got a working JMS client.

Most of the JMS 1.1 spec is covered.  A complete [coverage map with unit tests](https://github.com/skyscreamer/nevado/wiki/Master-Feature-Grid) provides more details to satisfy geeky curiosity.

We welcome feedback at nevado-dev@skyscreamer.org!
