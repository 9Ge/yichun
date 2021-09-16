package com.enercomn.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

public class JwtHelper {

    public final static String USERID = "userId";
    public final static String ROLEID = "roleId";
    public final static String AREAID = "areaId";
    public final static String LOGINNAME = "loginName";
    public final static String USETLEVEL = "userLevel";
    public final static String PROJECTID = "projectId";

    public static Claims parseJWT(String jsonWebToken, String base64Security) {
        try {
            Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(base64Security)).parseClaimsJws(jsonWebToken).getBody();
            return claims;
        } catch (Exception ex) {
            return null;
        }
    }

    public static String createJWT(String userName, int userId, long TTLMillis, String base64Security) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT").claim("uniqueName", userName).claim(USERID, userId).signWith(signatureAlgorithm, signingKey);
        //添加Token过期时间
        if (TTLMillis >= 0) {
            long expMillis = nowMillis + TTLMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp).setNotBefore(now);
        }

        //生成JWT
        return builder.compact();
    }

    public static String createJWTApp(String loginName, String userId, long TTLMillis, String base64Security) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT").claim("loginName", loginName).claim("userId", userId).signWith(signatureAlgorithm, signingKey);
//        //添加Token过期时间
//        if (TTLMillis >= 0) {
//            long expMillis = nowMillis + TTLMillis;
//            Date exp = new Date(expMillis);
//            builder.setExpiration(exp).setNotBefore(now);
//        }

        //生成JWT
        return builder.compact();
    }

    public static String createJWTApp(String roleId,String loginName, String cTciId, String userLevel,String projectId,String base64Security) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        System.out.println("loginName:"+loginName+",cTciId"+cTciId+",userLevel"+userLevel);
        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                .claim("loginName", loginName)
                .claim(USERID, cTciId)
                .claim(USETLEVEL, userLevel)
                .claim(ROLEID, roleId)
                .claim(PROJECTID, projectId)
                .signWith(signatureAlgorithm, signingKey);
        System.out.println("token的值为： "+Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(base64Security)).parseClaimsJws(builder.compact()).getBody());
        //生成JWT
        return builder.compact();
    }
}
