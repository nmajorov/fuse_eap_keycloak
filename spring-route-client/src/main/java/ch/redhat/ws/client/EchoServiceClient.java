package ch.redhat.ws.client;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBusFactory;
import org.slf4j.Logger;
import  com.redhat.consulting.ws.Echo;

/**
 * client launcher for EchoWebservice
 *
 * @author Nikolaj Majorov
 *
 **/
import org.slf4j.LoggerFactory;

public class EchoServiceClient {
	private static final Logger LOG = LoggerFactory.getLogger(EchoServiceClient.class);
	public final static QName SERVICE_QNAME = new QName("http://ws.consulting.redhat.com", "EchoService");
	public final static QName PORT_QNAME = new QName("http://ws.consulting.redhat.com", "EchoPort");

	public static void main(String[] args) {

		LOG.info("start echo service client");

		SpringBusFactory bf = new SpringBusFactory();
		URL busFile = EchoServiceClient.class.getClassLoader().getResource("client.xml");

		Bus bus = bf.createBus(busFile.toString());
		SpringBusFactory.setDefaultBus(bus);
		SpringBusFactory.setThreadDefaultBus(bus);

		URL wsdl = EchoServiceClient.class.getResource("/wsdls/Echo.wsdl");
		Service service = Service.create(wsdl, SERVICE_QNAME);

		Echo echoPort = service.getPort(PORT_QNAME, Echo.class);

		LOG.info("calling echo service ...");
		String response = echoPort.echo("hey ho snow");
		LOG.info("get responce: " + response);
	}
}
