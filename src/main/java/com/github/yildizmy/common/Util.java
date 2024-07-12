package com.github.yildizmy.common;

import java.util.UUID;

import static com.github.yildizmy.common.Constants.INVALID_ACCOUNT_ID_LENGTH;

public class Util {

    public static UUID formatUuid(String accountId) {
        String cleanedAccountId = accountId.replace("-", "");

        if (cleanedAccountId.length() != 32) {
            throw new IllegalArgumentException(INVALID_ACCOUNT_ID_LENGTH);
        }

        String formattedAccountId = String.format(
                "%s-%s-%s-%s-%s",
                cleanedAccountId.substring(0, 8),
                cleanedAccountId.substring(8, 12),
                cleanedAccountId.substring(12, 16),
                cleanedAccountId.substring(16, 20),
                cleanedAccountId.substring(20, 32)
        );
        return UUID.fromString(formattedAccountId);
    }
}
