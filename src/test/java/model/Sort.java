package model;

public class Sort {
    public enum SortCases {
        FIRST_POPULAR("First popular"),
        FIRST_NOT_POPULAR("First not popular"),
        FIRST_NEW("First new"),
        FIRST_OLD("First old"),
        PRICE_LOWEST_FIRST("Price: lowest first"),
        PRICE_HIGHEST_FIRST("Price: highest first");

        private final String value;

        SortCases(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
