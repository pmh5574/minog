package com.minog.minog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class TokenInfo {

    private String grantType;
    private String accessToken;
    private String refreshToken;

    public static TokenInfo.TokenInfoBuilder builder() {
        return new TokenInfo.TokenInfoBuilder();
    }

    public static class TokenInfoBuilder {
        private String grantType;
        private String accessToken;
        private String refreshToken;

        TokenInfoBuilder() {
        }

        public TokenInfo.TokenInfoBuilder grantType(final String grantType) {
            if (grantType != null) {
                this.grantType = grantType;
            }
            return this;
        }

        public TokenInfo.TokenInfoBuilder accessToken(final String accessToken) {
            if (accessToken != null) {
//                this.accessToken = TokenInfoBuilder.this.accessToken;
                this.accessToken = accessToken;
            }
            return this;
        }

        public TokenInfo.TokenInfoBuilder refreshToken(final String refreshToken) {
            if (refreshToken != null) {
                this.refreshToken = refreshToken;
            }
            return this;
        }


        public TokenInfo build() {
            return new TokenInfo(this.grantType, this.accessToken, this.refreshToken);
        }


        @Override
        public String toString() {
            return "TokenInfo.TokenInfoBuilder(grantType='" + grantType + '\'' + ", accessToken='" + accessToken + '\'' + ", refreshToken='" + refreshToken + '\'' + ')';
        }
    }
}
