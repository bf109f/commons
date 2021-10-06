package com.hxg.api.config.cpi;

/**
 *
 */
public enum PropertyKey
{

    HTTP_CONNECT_TIME_OUT("cuco.http.connectTimeout"),

    HTTP_SOCKET_TIME_OUT("cuco.http.socketTimeout"),

    HTTP_CONNECTION_REQUEST_TIME_OUT("cuco.http.connectionRequestTimeout"),
    ;
    private String keyName;

    /**
     * Initializes each enum element with the proper key name to be used in the connection string or properties maps.
     *
     * @param keyName
     *            the key name for the enum element.
     */
    PropertyKey(String keyName)
    {
            this.keyName = keyName;
    }

    /**
     * Gets the key name of this enum element.
     *
     * @return
     *         the key name associated with the enum element.
     */
    public String getKeyName()
    {
        return this.keyName;
    }
}
