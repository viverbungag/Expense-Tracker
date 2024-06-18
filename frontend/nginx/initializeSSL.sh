#!/bin/sh
set -e

OPENSSL_INSTALLED=false

if which openssl >/dev/null
then 
  OPENSSL_INSTALLED=true
fi

## certificate parameters
COUNTRY_NAME="PH"
STATE_NAME="Davao City"
LOCALITY_NAME="Davao City"
ORGANIZATION_NAME="Countable"
ORGANIZATIONAL_UNIT_NAME="Engineering Department"
COMMON_NAME="www.countable.com"
EMAIL_ADDRESS="viverbungag1@orangeandbronze.com"

## apache or nginx
SERVER_KEY="selfsigned.key"
SERVER_KEY_PATH="/etc/ssl/private"
SERVER_CRT="selfsigned.crt"
SERVER_CRT_PATH="/etc/ssl/certs"

OPENSSL_SUBJ_OPTIONS="
Country Name (2 letter code) [AU]:$COUNTRY_NAME
State or Province Name (full name) [Some-State]:$STATE_NAME
Locality Name (eg, city) []:$LOCALITY_NAME
Organization Name (eg, company) [Internet Widgits Pty Ltd]:$ORGANIZATION_NAME
Organizational Unit Name (eg, section) []:$ORGANIZATIONAL_UNIT_NAME
Common Name (e.g. server FQDN or YOUR name) []:$COMMON_NAME
Email Address []:$EMAIL_ADDRESS
"

if [ "$OPENSSL_INSTALLED" = true ]
then 
    echo "generating self signed certificate"
    echo "with these options: "
    echo "$OPENSSL_SUBJ_OPTIONS"
    echo ""

    ## generate self signed certificate
    openssl req \
        -new \
        -newkey rsa:4096 \
        -days 365 \
        -nodes \
        -x509 \
        -subj "/emailAddress=$EMAIL_ADDRESS/C=$COUNTRY_NAME/ST=$STATE_NAME/L=$LOCALITY_NAME/O=$ORGANIZATION_NAME/OU=$ORGANIZATIONAL_UNIT_NAME/CN=$COMMON_NAME" \
        -keyout $SERVER_KEY \
        -out $SERVER_CRT
    
    # move key to correct location
    mv -f $SERVER_KEY $SERVER_KEY_PATH/$SERVER_KEY
    mv -f $SERVER_CRT $SERVER_CRT_PATH/$SERVER_CRT
else
    echo "openssl is not installed"
    exit 1
fi

#end