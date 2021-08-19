package com.ssafy.backend.coupon.service;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.hibernate.internal.build.AllowSysOut;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.python.icu.impl.number.Parse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.backend.coupon.mapper.CouponMapper;
import com.ssafy.backend.coupon.model.GameIDModel;

@Service
public class CouponServiceImpl implements CouponService {
	
	static class cookiecoupon{
		String reward;
		String code;
		String date;
		public cookiecoupon(String reward, String code, String date) {
			super();
			this.reward = reward;
			this.code = code;
			this.date = date;
		}
	}
	
	final String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36";
	
	@Autowired
	private CouponMapper mapper;
	
	@Override
	public List<String> getAFKArenaCoupon() throws Exception {
		final String URL = "https://www.afkarena.net/redemption-codes";
		
		Document doc = Jsoup.connect(URL).get();
		
		Elements couponList = doc.select("input");
		
		List<String> list = new ArrayList<>();
		
		for(Element c : couponList) {
			String coupon = c.val();
			if(coupon.equals("")) {
				break;
			} else {
				list.add(coupon);
			}
		}
		
		return list;
	}
	
	
	@Override
	public List<cookiecoupon> getCookkieCoupon() throws Exception {
		final String URL="https://ui-tricks.netlify.app/cookieCoupon/";
		Document doc = Jsoup.connect(URL).get();
		Elements couponList = doc.select("td");
		List<String> list=new ArrayList<>();
		List<cookiecoupon> cookie =new ArrayList<CouponServiceImpl.cookiecoupon>();
		for(int i=0;i<couponList.size();i+=3) {
			String reward=couponList.get(i).text();
			String code=couponList.get(i+1).text();
			code=code.substring(0,code.length()-3);
			String date=couponList.get(i+2).text();
			if(date.equals("유효"))continue;
			date=date.substring(2,date.length());
			String temp="";
			Calendar cal= Calendar.getInstance();
			int month=cal.get(Calendar.MONTH)+1;
			int day=cal.get(Calendar.DAY_OF_MONTH);
			temp+=month+"."+day;
			float datevalue=Float.parseFloat(date)-Float.parseFloat(temp);
			if(datevalue>=0)cookie.add(new cookiecoupon(reward, code, date));
		}
		return cookie;
	}

	
	
	@Override
	public int updateAFKArenaUID(GameIDModel model) throws Exception {
		return mapper.updateAFKArenaUID(model);
	}
	
	@Override
	public GameIDModel getGameUID(String userID) throws Exception {
		return mapper.getGameUID(userID);
	}
	
	
	@Override
	public void registCookierunCoupon(String userID) throws Exception {
		final String URL="https://account.devplay.com/v2/coupon/ck";
		List<cookiecoupon> couponList=getCookkieCoupon();
		for(cookiecoupon x:couponList) {
			String jsonBody="{\r\n"
					+ "  \"email\": \""+userID+"\",\r\n"
					+ "  \"coupon_code\": \""+x.code+"\"\r\n"
					+ "}";

			Connection.Response registCoupon=Jsoup.connect(URL)
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36")
					.header("Content-Type", "application/json;charset=UTF-8")
					.header("Accept", "application/json, */*")
					.followRedirects(true)
					.ignoreHttpErrors(true)
					.ignoreContentType(true)
					.header("referer", "https://game.devplay.com/")
					.requestBody(jsonBody)
					.method(Connection.Method.POST)
					.execute();	
		}
	}
	
	// 132597005
	@Override
	public void registAFKArenaCoupon(String userID, String verifyCode) throws Exception {
		
		final String URL = "https://cdkey.lilith.com/api/verify-afk-code";
		
		String AFKArenaUID = mapper.selectAFKArenaUID(userID);
		
		String jsonBody = "{\"uid\":" + AFKArenaUID + ", \"game\":\"afk\", \"code\" : \"" + verifyCode + "\"}";
		setSSL();
		
		Connection.Response getLoginCookie = Jsoup.connect(URL)
				.userAgent(userAgent)
				.header("Content-Type", "application/json;charset=UTF-8")
				.header("Accept", "application/json")
                .followRedirects(true)
                .ignoreHttpErrors(true)
				.ignoreContentType(true)
				.header("referer", "https://cdkey.lilith.com/afk-global")
				.requestBody(jsonBody)
				.method(Connection.Method.POST)
				.execute();
		
		String registURL = "https://cdkey.lilith.com/api/cd-key/consume";
		List<String> couponList = getAFKArenaCoupon();
		
		for(String coupon : couponList) {
			String jsonBody1 = "{\"type\":\"cdkey_web\", \"game\":\"afk\", \"uid\" : " + AFKArenaUID + ", \"cdkey\" : \""+ coupon +"\"}";
			Connection.Response registCoupon = Jsoup.connect(registURL)
					.userAgent(userAgent)
					.header("Content-Type", "application/json;charset=UTF-8")
					.header("Accept", "application/json")
					.cookies(getLoginCookie.cookies())
	                .followRedirects(true)
	                .ignoreHttpErrors(true)
					.ignoreContentType(true)
					.header("referer", "https://cdkey.lilith.com/afk-global")
					.requestBody(jsonBody1)
					.method(Connection.Method.POST)
					.execute();
		}
	}
	
	public static void setSSL() throws NoSuchAlgorithmException, KeyManagementException {
		TrustManager[] trustAllCerts = new TrustManager[] {
			new X509TrustManager() {
				public X509Certificate[] getAcceptedIssuers() { return null; }

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


	@Override
	public int updateCookierunUID(GameIDModel model) throws Exception {
		return mapper.updateCookierunUID(model);
	}


	@Override
	public String selectCookierunUID(String userID) throws Exception {
		return mapper.selectCookierunUID(userID);
	}

}
