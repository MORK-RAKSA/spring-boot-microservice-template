/*
 * Copyright 2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.raksa.app.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class IdGenerator {

    /**
     * Generates a unique ID based on the current date and a random sequence number.
     *
     * @param prefix the prefix to prepend to the generated ID
     * @return a unique ID in the format: prefix + YYYYMMDD + 6-digit sequence number
     */
    public static String generate(String prefix) {
        LocalDateTime date = LocalDateTime.now();
//        long sequence = ThreadLocalRandom.current().nextInt(100000);
        return String.format("%s%s%s", prefix, date.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssnnnnnnnnn")), random());
    }

    /**
     * Generates a unique ID based on the current date and a random sequence number.
     *
     * @return a unique ID in the format: YYYYMMDD + 6-digit sequence number
     */
    public static String generateUUID(String prefix) {
        return String.format("%s%s", prefix, java.util.UUID.randomUUID().toString().replace("-", ""));
    }

    /**
     * Generates a UUID without any prefix.
     *
     * @return a UUID string with dashes removed
     */
    public static String generateUUID() {
        return String.format("%s", java.util.UUID.randomUUID().toString().replace("-", ""));
    }

    private static String random() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            int idx = (int) (Math.random() * chars.length());
            sb.append(chars.charAt(idx));
        }
        return sb.toString();
    }
}
