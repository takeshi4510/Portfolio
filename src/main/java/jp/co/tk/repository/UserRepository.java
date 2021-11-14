package jp.co.tk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jp.co.tk.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{

	//ログイン時の存在チェック
	@Query(value="select * from user where name = ?1 and pass = ?2"
			, nativeQuery=true)
	public List<UserEntity> findbyUserAndPassword(String name, String password);

	//ログイン登録時のユーザチェック
	@Query(value="select * from user where name = ?1"
			, nativeQuery= true)
	public List<UserEntity> findbyUser(String name);

	//idを取得
	@Query(value="select id from user where name = ?1 and pass = ?2"
			, nativeQuery=true)
	public int findById(String name, String password);

	//ログインしたidをもとにユーザネームを取得
	@Query(value="select name from user where id = ?1"
			,nativeQuery=true)
	public String findUserNameById(Object userId);


}