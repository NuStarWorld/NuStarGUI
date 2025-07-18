package top.nustar.nustargui;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class Pair <K, V>{
    private final K key;
    private final V value;
}
