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
			System.out.println(coupon);
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
		System.out.println("여기는?");
		Document doc = Jsoup.connect(URL).get();
		Elements couponList = doc.select("td");
		List<String> list=new ArrayList<>();
		List<cookiecoupon> cookie =new ArrayList<CouponServiceImpl.cookiecoupon>();
//		for(Element c :couponList) {
//			String coupon=c.text();
//			
//			System.out.println(coupon);
//		}
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
		setSSL();
		for(cookiecoupon x:couponList) {
			String jsonBody="{email: \"" + userID + "\", coupon_code: \"" + x.code + "\"}";
			System.out.println(jsonBody);
			Connection.Response registCoupon=Jsoup.connect(URL)
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36")
					.header("Content-Type", "application/json;charset=UTF-8")
					.header("Accept", "application/json")
					.followRedirects(true)
					.ignoreHttpErrors(true)
					.ignoreContentType(true)
					.header("referer", "https://game.devplay.com/")
					.requestBody(jsonBody)
					.method(Connection.Method.POST)
					.execute();
			System.out.println(registCoupon.statusCode());		
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
		
		System.out.println(getLoginCookie.statusCode());
		
		String registURL = "https://cdkey.lilith.com/api/cd-key/consume";
		List<String> couponList = getAFKArenaCoupon();
		
		for(String coupon : couponList) {
			String jsonBody1 = "{\"type\":\"cdkey_web\", \"game\":\"afk\", \"uid\" : " + AFKArenaUID + ", \"cdkey\" : \""+ coupon +"\"}";
			Connection.Response registCoupon = Jsoup.connect(registURL)
					.userAgent(userAgent)
					.header("Content-Type", "application/json;charset=UTF-8")
					.header("Accept", "application/json")
					.cookies(getLoginCookie.cookies())
//					.data("id", AFKArenaUID)
	                .followRedirects(true)
	                .ignoreHttpErrors(true)
					.ignoreContentType(true)
					.header("referer", "https://cdkey.lilith.com/afk-global")
					.requestBody(jsonBody1)
					.method(Connection.Method.POST)
					.execute();
			System.out.println(registCoupon.statusCode());
//			int res = registCoupon.statusCode();
//			if(res == 200) {
//				System.out.println("쿠폰 입력 성공");
//			} else {
//				System.out.println("사용 기간이 만료되었거나 이미 사용이 완료된 쿠폰입니다.");
//			}
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

}
