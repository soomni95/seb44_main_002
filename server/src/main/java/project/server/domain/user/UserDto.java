package project.server.domain.user;

import lombok.Builder;
import lombok.Getter;
import project.server.domain.cocktail.dto.CocktailDto;

import java.util.List;

public class UserDto {

    @Getter
    public static class post {
        private String email;
        private String password;
        private String name;
        private String gender;
        private int age;

        public User postToEntity() {
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            user.setName(name);
            user.setGender(gender);
            user.setAge(age);

            return user;
        }
    }

    @Getter
    @Builder
    public static class Response {
        private long userId;
        private String name;
        private String profileImageUrl;
        private String gender;
        private int age;
        private String email;
        private long subscriberCount;
        private List<CocktailDto.SimpleResponse> cocktails;
        private List<CocktailDto.SimpleResponse> bookmarkedCocktails;
    }

    @Getter
    public static class Patch {
        private long userId;
        private String password;
    }
}
