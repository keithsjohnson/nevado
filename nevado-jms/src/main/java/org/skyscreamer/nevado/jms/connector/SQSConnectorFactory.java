package org.skyscreamer.nevado.jms.connector;

import javax.jms.JMSException;

import com.amazonaws.auth.AWSCredentials;

/**
 * Factory for SQSConnector objects.
 *
 * @author Carter Page <carter@skyscreamer.org>
 */
public interface SQSConnectorFactory {
	SQSConnector getInstance(String awsAccessKey, String awsSecretKey) throws JMSException;

	SQSConnector getInstance(String awsAccessKey, String awsSecretKey, String awsSQSEndpoint, String awsSNSEndpoint)
			throws JMSException;

	SQSConnector getInstance(AWSCredentials awsCredentials, String awsSQSEndpoint, String awsSNSEndpoint)
			throws JMSException;
}
