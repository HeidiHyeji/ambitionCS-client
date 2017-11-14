// IClientServiceCallback.aidl
package com.example.gogo6.myapp;
// Declare any non-default types here with import statements

interface IClientServiceCallback {
     oneway void readMsgFromServer(String read_msg);
}
