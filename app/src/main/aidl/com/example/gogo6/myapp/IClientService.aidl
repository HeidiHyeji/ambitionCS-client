// IClientService.aidl
package com.example.gogo6.myapp;

import com.example.gogo6.myapp.IClientServiceCallback;
// Declare any non-default types here with import statements

interface IClientService {
    oneway void writeMsgToServer(String write_msg, IClientServiceCallback callback);
}
