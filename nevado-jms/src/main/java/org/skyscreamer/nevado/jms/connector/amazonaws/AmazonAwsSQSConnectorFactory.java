package org.skyscreamer.nevado.jms.connector.amazonaws;

import javax.jms.JMSException;

import org.apache.commons.lang.StringUtils;
import org.skyscreamer.nevado.jms.connector.AbstractSQSConnectorFactory;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;

/**
 * Connectory factory for Amazon AWS connector.
 *
 * @author Carter Page <carter@skyscreamer.org>
 */
public class AmazonAwsSQSConnectorFactory extends AbstractSQSConnectorFactory {
	protected boolean _useAsyncSend = false;

	private boolean _testAlwaysPasses = false;

	@Override
	public AmazonAwsSQSConnector getInstance(String awsAccessKey, String awsSecretKey, String awsSQSEndpoint,
			String awsSNSEndpoint) {
		return getInstance(awsSQSEndpoint, awsSNSEndpoint, createConnector(awsAccessKey, awsSecretKey));
	}

	@Override
	public AmazonAwsSQSConnector getInstance(AWSCredentials awsCredentials, String awsSQSEndpoint,
			String awsSNSEndpoint) throws JMSException {
		return getInstance(awsSQSEndpoint, awsSNSEndpoint, createConnector(awsCredentials));
	}

	private AmazonAwsSQSConnector getInstance(String awsSQSEndpoint, String awsSNSEndpoint,
			AmazonAwsSQSConnector amazonAwsSQSConnector) {
		amazonAwsSQSConnector.setTestAlwaysPasses(_testAlwaysPasses);
		if (StringUtils.isNotEmpty(awsSQSEndpoint)) {
			amazonAwsSQSConnector.getAmazonSQS().setEndpoint(awsSQSEndpoint);
			amazonAwsSQSConnector.getAmazonSQS().setRegion(Region.getRegion(Regions.fromName("eu-west-1")));
		}
		if (StringUtils.isNotEmpty(awsSNSEndpoint)) {
			amazonAwsSQSConnector.getAmazonSNS().setEndpoint(awsSNSEndpoint);
			amazonAwsSQSConnector.getAmazonSNS().setRegion(Region.getRegion(Regions.fromName("eu-west-1")));
		}
		return amazonAwsSQSConnector;
	}

	protected AmazonAwsSQSConnector createConnector(AWSCredentials awsCredentials) {
		return new AmazonAwsSQSConnector(awsCredentials, _isSecure, _receiveCheckIntervalMs, _useAsyncSend);
	}

	protected AmazonAwsSQSConnector createConnector(String awsAccessKey, String awsSecretKey) {
		return new AmazonAwsSQSConnector(awsAccessKey, awsSecretKey, _isSecure, _receiveCheckIntervalMs, _useAsyncSend);
	}

	public void setUseAsyncSend(boolean useAsyncSend) {
		_useAsyncSend = useAsyncSend;
	}

	public boolean isUseAsyncSend() {
		return _useAsyncSend;
	}

	public void setTestAlwaysPasses(boolean _testAlwaysPasses) {
		this._testAlwaysPasses = _testAlwaysPasses;
	}

}
