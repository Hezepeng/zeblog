package com.zeblog.util;

import com.zeblog.common.Const;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.spec.SecretKeySpec;
import javax.faces.component.html.HtmlPanelGrid;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * @author: Hezepeng
 * @email: hezepeng96@foxmail.com
 * @date: 2019-07-23 15:43
 */
public class TokenUtil {

    public static String createJWT(String id, String subject){

        if(StringUtils.isEmpty(id)||StringUtils.isEmpty(subject)){
            throw new IllegalArgumentException(" JWT String argument cannot be null or empty.");
        }
        //定义签名的加密方法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        //签名签发的时间
        Date now = new Date();

        //签名过期的时间
        long expMillis = System.currentTimeMillis() + Const.TOKEN_VALID_TIME;
        Date exp = new Date(expMillis);

        byte[] secretBytes = DatatypeConverter.parseBase64Binary(Const.TOKEN_SECRET);
        Key signatureKey = new SecretKeySpec(secretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder jwtBuilder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(Const.TOKEN_ISSUER)
                .setExpiration(exp)
                .signWith(signatureAlgorithm, signatureKey);
        return jwtBuilder.compact();
    }

    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(Const.TOKEN_SECRET))
                .parseClaimsJws(token)
                .getBody();
    }

    public static Integer getUserId(String token) {
        Claims claims = parseToken(token);
        return Integer.valueOf(claims.getId());
    }

    public static String getUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(Const.TOKEN_SECRET))
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public static Integer getUserIdFromRequest(HttpServletRequest request) {
        String token = request.getHeader(Const.TOKEN_HEADER_NAME);
        return getUserId(token);
    }

    public static String getUsernameByRequest(HttpServletRequest request) {
        String token = request.getHeader(Const.TOKEN_HEADER_NAME);
        return getUsername(token);
    }
}
