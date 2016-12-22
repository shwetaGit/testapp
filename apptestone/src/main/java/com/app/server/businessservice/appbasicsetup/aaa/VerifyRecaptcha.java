package com.app.server.businessservice.appbasicsetup.aaa;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import atg.taglib.json.util.JSONObject;

import com.athena.config.appsetUp.interfaces.AppConfigurationInterface;

public class VerifyRecaptcha {

	public static final String URL = "https://www.google.com/recaptcha/api/siteverify";
	public static final String SECRETE = "6Lc1jA0TAAAAAFKHoZfFxXQgpxPNZLLqjLKOwbqz";

	/****
	 * VERIFYING RECAPTCHA AT LOGIN
	 */
	public static boolean verify(String gRecaptchaResponse, AppConfigurationInterface appConfiguration) throws IOException {
		if (gRecaptchaResponse == null || "".equals(gRecaptchaResponse)) {
			return false;
		}

		try {
			URL obj = new URL(URL);
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

			// add reuqest header
			con.setRequestMethod("POST");
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

			String postParams = "secret=" + SECRETE + "&response=" + gRecaptchaResponse;

			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(postParams);
			wr.flush();
			wr.close();

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			JSONObject jsonObject = new JSONObject(response.toString());

			return jsonObject.getBoolean("success");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
