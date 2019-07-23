package com.zeblog.util;

import com.zeblog.common.Const;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * @author: Hezepeng
 * @email: hezepeng96@foxmail.com
 * @date: 2019-07-23 15:43
 */
public class TokenUtil {

    public static String createJWT(String id, String subject) {

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

    public static Claims parseToken(String token){
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(Const.TOKEN_SECRET))
                .parseClaimsJws(token)
                .getBody();
    }
}
