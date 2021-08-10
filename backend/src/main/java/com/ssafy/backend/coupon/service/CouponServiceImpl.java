package com.ssafy.backend.coupon.service;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.backend.coupon.mapper.CouponMapper;
import com.ssafy.backend.coupon.model.GameIDModel;
import com.ssafy.backend.util.CustomSSLSocketFactory;

@Service
public class CouponServiceImpl implements CouponService {
	
	final String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36";
	
	@Autowired
	private CouponMapper mapper;
	
	@Override
	public void getAFKArenaCoupon() throws Exception {
		final String URL = "https://www.afkarena.net/redemption-codes";
		
		Document doc = Jsoup.connect(URL).get();
		
		Elements couponList = doc.select("input");
		
		for(Element c : couponList) {
			String coupon = c.val();
			System.out.println(coupon);
			if(coupon.equals("")) {
				break;
			}
		}
	}

	@Override
	public int updateAFKArenaUID(GameIDModel model) throws Exception {
		return mapper.updateAFKArenaUID(model);
	}

	
	// 132597005
	@Override
	public void registAFKArenaCoupon(String userID, String verifyCode) throws Exception {
		
		final String URL = "https://cdkey.lilith.com/api/verify-afk-code";
		
		String AFKArenaUID = mapper.selectAFKArenaUID(userID);
		
		setSSL();
		
		Connection.Response getLoginCookie = Jsoup.connect(URL)
				.userAgent(userAgent)
				.header("Content-Type", "application/json;charset=UTF-8")
				.ignoreContentType(true)
				.header("referer", "https://cdkey.lilith.com/afk-global")
				.data("code", verifyCode)
				.data("game", "afk")
				.data("uid", AFKArenaUID)
				.method(Connection.Method.POST)
				.execute();
		
		
		String registURL = "https://cdkey.lilith.com/afk-global";
		String couponCode = "xiaban886";
		
		Connection.Response registCoupon = Jsoup.connect(registURL)
				.userAgent(userAgent)
				.header("Content-Type", "application/json;charset=UTF-8")
				.ignoreContentType(true)
				.header("referer", "https://cdkey.lilith.com/afk-global")
//				.sslSocketFactory(new CustomSSLSocketFactory())
				.cookies(getLoginCookie.cookies())
				.data("cdkey", couponCode)
				.data("game", "afk")
				.data("type", "cdkey_web")
				.data("uid", AFKArenaUID)
				.method(Connection.Method.POST)
				.execute();
		
		System.out.println(registCoupon.statusCode());
				
	}
	
	public static void setSSL() throws NoSuchAlgorithmException, KeyManagementException {
		TrustManager[] trustAllCerts = new TrustManager[] {
			new X509TrustManager() {
				public X509Certificate[] getAcceptedIssuers() { return null; }
//				​public void checkClientTrusted(X509Certificate[] certs, String authType) {}​
//				public void checkServerTrusted(X509Certificate[] certs, String authType) {}

				@Override
				public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}

				@Override
				public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
			}
		};
		
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, new SecureRandom());
		
		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
			@Override
			public boolean verify(String hostname, SSLSession session) { return true; }
		}); 
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory()); 
	}
}
