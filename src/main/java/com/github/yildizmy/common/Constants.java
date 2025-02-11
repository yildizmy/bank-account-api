package com.github.yildizmy.common;

public final class Constants {

    private Constants() {
        throw new UnsupportedOperationException(CLASS_CANNOT_BE_INSTANTIATED);
    }

    public static final String TRACE = "trace";
    public static final String SUCCESS = "Success";
    public static final String VALIDATION_ERROR = "Validation error. Check 'errors' field for details";
    public static final String CLASS_CANNOT_BE_INSTANTIATED = "This is a utility class and cannot be instantiated";
    public static final String REQUESTED_ACCOUNT_NOT_FOUND = "Requested account is not found";
    public static final String ACCOUNT_NOT_FOUND = "Account is not found";
    public static final String INSUFFICIENT_BALANCE = "Insufficient Balance: Cannot debit {0} from account number {1}";
    public static final String INSUFFICIENT_BALANCE_EXCEPTION = "Debit operation aborted: Insufficient balance";
    public static final String ILLEGAL_STATE_EXCEPTION = "IllegalState exception";
    public static final String METHOD_ARGUMENT_NOT_VALID = "MethodArgumentNotValid exception";
    public static final String INVALID_ACCOUNT_ID_LENGTH = "Invalid account ID length. Expected 32 characters after removing hyphens";
    public static final String HANDLING_ACCOUNT_CREATION_COMMAND = "Handling a Bank Account creation command {}";
    public static final String HANDLING_ACCOUNT_CREDIT_COMMAND = "Handling a Bank Account Credit command {}";
    public static final String HANDLING_ACCOUNT_DEBIT_COMMAND = "Handling a Bank Account Debit command {}";
    public static final String HANDLING_FIND_BANK_ACCOUNT_QUERY = "Handling FindBankAccountQuery query: {}";
}
