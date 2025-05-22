    public static void main(String[] args) {
        char[] characters = "0123456789abcdefghijkmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        char[] excludeCharacters = {'1', 'L', 'I','l', '0', 'o','O', '2', 'z', 'Z', '9', 'g'};
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        while (code.length() < 4) {
            int index = random.nextInt(characters.length);
            char c = characters[index];
            boolean isExcluded = false;
            for (char exclude : excludeCharacters) {
                if (c == exclude) {
                    isExcluded = true;
                    break;