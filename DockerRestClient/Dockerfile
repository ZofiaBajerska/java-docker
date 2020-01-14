FROM alpine:3.11.2
ENV ADDRESS=192.168.0.150:8080/user/list
RUN apk update && apk upgrade && \
   apk add curl
CMD curl -s $ADDRESS
