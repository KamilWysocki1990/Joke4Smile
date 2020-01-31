package j4s.com.jokeforsmile.repository.dao;


import java.util.List;

import j4s.com.jokeforsmile.repository.RandomJokes;

public interface JokesDaoInterface {


  //  List<RandomJokes> getAll();
    void insert(RandomJokes randomJokes);
    void delete(RandomJokes randomJokes);
   RandomJokes getJokeById(int jokeId);
  List<RandomJokes> getJokeByAccount(String account);
}
