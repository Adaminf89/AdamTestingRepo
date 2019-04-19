/*Adam S Infiesto
* Java 2
* Net */

package com.example.adaminfiesto.infiestoadamce01;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;
import org.apache.commons.io.IOUtils;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

class Net
{
    public static boolean isConnected(Context _context)
    {

    ConnectivityManager mgr = (ConnectivityManager)_context.getSystemService(Context.CONNECTIVITY_SERVICE);

    if(mgr != null)
    {
        NetworkInfo info = mgr.getActiveNetworkInfo();
        if(info != null) {
            return info.isConnected();
        }
        else
        {
            Toast daToast = Toast.makeText(_context, "No Service connection", Toast.LENGTH_SHORT);
            daToast.show();
            return false;
        }

    }
    return false;}


    public static String getNetworkData(String _url) {
        HttpURLConnection connection = null;
        InputStream is = null;

        try
        {
            URL url = new URL(_url);

            connection = (HttpURLConnection)url.openConnection();

            connection.connect();

            is = connection.getInputStream();

            String data = IOUtils.toString(is,"UTF-8");

            is.close();

            connection.disconnect();
            ///gotta return some thing
            return data;
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            // If we have a stream, try to close it.
            if(connection != null)
            {
                if(is != null)
                {
                    try
                    {
                        is.close();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                connection.disconnect();
            }

        }
        return null;
    }
}
