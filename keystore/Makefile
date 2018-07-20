PASSWORD=31c2cd87f0078502929b1691f83eed6c
KEYSTORE=keystore.jks
HOSTNAME=localhost

# CN = Common Name
# OU = Organization Unit
# O  = Organization Name
# L  = Locality Name
# ST = State Name
# C  = Country (2-letter Country Code)
# E  = Email
DNAME_CA='CN=DEV Zucacorp CA,OU=zucacorp.com,O=Zuca Corp,L=São José dos Campos,ST=SP,C=BR'
# For server certificates, the Common Name (CN) must be the hostname
DNAME_HOST='CN=$(HOSTNAME),OU=zucacorp.com,O=Zuca Corp,L=São José dos Campos,ST=SP,C=BR'
TRUSTSTORE=truststore.jks

all:	clean create-keystore add-host create-truststore

create-jwt-key:
	# Generate JWT KEY AND SAVE PUBLIC KEY
	keytool -genkeypair -alias jwt \
	    -keyalg RSA -keypass $(PASSWORD) -dname $(DNAME_CA) \
	    -keystore jwt.jks -storepass $(PASSWORD)

	keytool -importkeystore -srckeystore jwt.jks -destkeystore jwt.jks -deststoretype pkcs12 -srcstorepass $(PASSWORD)

	rm jwt.jks.old

	keytool -list -rfc --keystore jwt.jks -storepass $(PASSWORD) | openssl x509 -inform pem -pubkey >> jwt-pub

create-keystore:
	# Generate a certificate authority (CA)
	keytool -genkey -alias ca -ext san=dns:localhost,ip:127.0.0.1 -ext BC=ca:true \
	    -keyalg RSA -keysize 4096 -sigalg SHA512withRSA -keypass $(PASSWORD) \
	    -validity 3650 -dname $(DNAME_CA) \
	    -keystore $(KEYSTORE) -storepass $(PASSWORD)

add-host:
	# Generate a host certificate
	keytool -genkey -alias $(HOSTNAME) -ext san=dns:localhost,ip:127.0.0.1  \
	    -keyalg RSA -keysize 4096 -sigalg SHA512withRSA -keypass $(PASSWORD) \
	    -validity 3650 -dname $(DNAME_HOST) \
	    -keystore $(KEYSTORE) -storepass $(PASSWORD)
	# Generate a host certificate signing request
	keytool -certreq -alias $(HOSTNAME) -ext san=dns:localhost,ip:127.0.0.1 -ext BC=ca:true \
	    -keyalg RSA -keysize 4096 -sigalg SHA512withRSA \
	    -validity 3650 -file "$(HOSTNAME).csr" \
	    -keystore $(KEYSTORE) -storepass $(PASSWORD)
	# Generate signed certificate with the certificate authority
	keytool -gencert -alias ca -ext san=dns:localhost,ip:127.0.0.1 \
	    -validity 3650 -sigalg SHA512withRSA \
	    -infile "$(HOSTNAME).csr" -outfile "$(HOSTNAME).crt" -rfc \
	    -keystore $(KEYSTORE) -storepass $(PASSWORD)
	# Import signed certificate into the keystore
	keytool -import -trustcacerts -alias $(HOSTNAME) -ext san=dns:localhost,ip:127.0.0.1 \
	    -file "$(HOSTNAME).crt" \
	    -keystore $(KEYSTORE) -storepass $(PASSWORD)

export-authority:
	# Export certificate authority
	keytool -export -alias ca -ext san=dns:localhost,ip:127.0.0.1 -file ca.crt -rfc \
	    -keystore $(KEYSTORE) -storepass $(PASSWORD)

create-truststore: export-authority
	# Import certificate authority into a new truststore
	keytool -import -trustcacerts -noprompt -alias ca -ext san=dns:localhost,ip:127.0.0.1 -file ca.crt \
	    -keystore $(TRUSTSTORE) -storepass $(PASSWORD)

clean:
	# Remove generated artifacts
	find . \( -name "$(HOSTNAME)*" -o -name "$(KEYSTORE)" -o -name "$(TRUSTSTORE)" -o -name ca.crt -o -name "jwt*" \) -type f -exec rm -f {} \;
