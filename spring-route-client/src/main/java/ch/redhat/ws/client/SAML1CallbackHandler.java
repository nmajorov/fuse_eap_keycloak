package ch.redhat.ws.client;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.wss4j.common.crypto.Crypto;
import org.apache.wss4j.common.crypto.CryptoFactory;
import org.apache.wss4j.common.crypto.CryptoType;
import org.apache.wss4j.common.ext.WSSecurityException;
import org.apache.wss4j.common.saml.SAMLCallback;
import org.apache.wss4j.common.saml.bean.KeyInfoBean;
import org.apache.wss4j.common.saml.bean.SubjectBean;
import org.apache.wss4j.common.saml.bean.Version;
import org.apache.wss4j.common.saml.builder.SAML1Constants;

/**
 * A Callback Handler implementation for a SAML 1.1 assertion. By default it creates an
 * authentication assertion using Sender Vouches.
 */
public class SAML1CallbackHandler extends AbstractSAMLCallbackHandler {

    private boolean signAssertion;

    public SAML1CallbackHandler() throws Exception {
        if (certs == null) {
            Crypto crypto = CryptoFactory.getInstance("alice.properties");
            CryptoType cryptoType = new CryptoType(CryptoType.TYPE.ALIAS);
            cryptoType.setAlias("alice");
            certs = crypto.getX509Certificates(cryptoType);
        }

        subjectName = "uid=alice,ou=people,ou=saml-demo,o=example.com";
        subjectQualifier = "www.example.com";
        confirmationMethod = SAML1Constants.CONF_SENDER_VOUCHES;
    }

    public void handle(Callback[] callbacks)
        throws IOException, UnsupportedCallbackException {
        for (int i = 0; i < callbacks.length; i++) {
            if (callbacks[i] instanceof SAMLCallback) {
                SAMLCallback callback = (SAMLCallback) callbacks[i];
                callback.setIssuer("www.example.com");
                callback.setSamlVersion(Version.SAML_11);
                SubjectBean subjectBean =
                    new SubjectBean(
                        subjectName, subjectQualifier, confirmationMethod
                    );
                if (SAML1Constants.CONF_HOLDER_KEY.equals(confirmationMethod)) {
                    try {
                        KeyInfoBean keyInfo = createKeyInfo();
                        subjectBean.setKeyInfo(keyInfo);
                    } catch (Exception ex) {
                        throw new IOException("Problem creating KeyInfo: " +  ex.getMessage());
                    }
                }
                createAndSetStatement(subjectBean, callback);

                try {
                    Crypto crypto = CryptoFactory.getInstance("outsecurity.properties");
                    callback.setIssuerCrypto(crypto);
                    callback.setIssuerKeyName("myalias");
                    callback.setIssuerKeyPassword("myAliasPassword");
                    callback.setSignAssertion(signAssertion);
                } catch (WSSecurityException e) {
                    throw new IOException(e);
                }

            } else {
                throw new UnsupportedCallbackException(callbacks[i], "Unrecognized Callback");
            }
        }
    }

    public boolean isSignAssertion() {
        return signAssertion;
    }

    public void setSignAssertion(boolean signAssertion) {
        this.signAssertion = signAssertion;
    }

}
