package task3_3;

import rx.Observable;
import rx.functions.Func1;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class UserFriendSystem {
    private static final int TOTAL_USERS = 100;
    private static final int TOTAL_FRIENDS = 10;
    private static UserFriend[] userFriends;

    public static void main(String[] args) {
        Random random = new Random();
        userFriends = new UserFriend[TOTAL_USERS * TOTAL_FRIENDS];

        // Заполнение массива случайными данными
        for (int i = 0; i < userFriends.length; i++) {
            int userId = random.nextInt(TOTAL_USERS);
            int friendId = random.nextInt(TOTAL_USERS);
            userFriends[i] = new UserFriend(userId, friendId);
        }

        // Генерация массива случайных userId
        int[] randomUserIds = random.ints(0, TOTAL_USERS)
                .distinct()
                .limit(10) // Ограничиваем до 10 уникальных userId
                .toArray();

        // Преобразование массива userId в поток UserFriend
        List<Integer> userIdList = Arrays.stream(randomUserIds)
                .boxed()
                .collect(Collectors.toList());
        
        Observable.from(userIdList)
                .flatMap(new Func1<Integer, Observable<UserFriend>>() {
                    @Override
                    public Observable<UserFriend> call(Integer userId) {
                        return getFriends(userId);
                    }
                })
                .subscribe(System.out::println); // Вывод объектов UserFriend
    }

    // Функция, возвращающая поток объектов UserFriend по заданному userId
    public static Observable<UserFriend> getFriends(int userId) {
        List<UserFriend> userFriendList = Arrays.asList(userFriends);
        return Observable.from(userFriendList)
                .filter(userFriend -> userFriend.userId == userId); // Фильтрация по userId
    }
}
