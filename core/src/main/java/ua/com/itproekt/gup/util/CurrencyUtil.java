package ua.com.itproekt.gup.util;

import javax.xml.soap.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;

/**
 * The current course of bank currency
 */

public class CurrencyUtil {

    private SOAPMessage soapMessage;
    private final static String destination = "http://www.cbr.ru/DailyInfoWebServ/DailyInfo.asmx";

    public CurrencyUtil() throws SOAPException, TransformerException {
        soapMessage = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL).createMessage();
        SOAPPart           part = soapMessage.getSOAPPart();
        SOAPEnvelope   envelope = part.getEnvelope();
        SOAPBody           body = envelope.getBody();
        Name           bodyName = envelope.createName("AllDataInfoXML", null, "http://web.cbr.ru/");

        body.addBodyElement(bodyName);
        soapMessage.saveChanges();
    }

    /*
     * Sending messages and receiving a reply
     */
    public SOAPMessage send() throws SOAPException, TransformerException {
        SOAPConnectionFactory soapConnFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection             connection = soapConnFactory.createConnection();
        SOAPMessage                     reply = connection.call(soapMessage, destination);

        return reply;
    }

    /*
     * Checking the received answer
     */
    public Transformer get(SOAPMessage reply) throws SOAPException, TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer               transformer = transformerFactory.newTransformer();
        Source                  sourceContent = reply.getSOAPPart().getContent();
        StreamResult                   result = new StreamResult(System.out);
        transformer.transform(sourceContent, result);

        return transformer;
    }

}
