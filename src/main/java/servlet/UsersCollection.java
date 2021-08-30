package servlet;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
public class UsersCollection {
    private ConcurrentHashMap<String,String> userMap = new ConcurrentHashMap<>();
}
