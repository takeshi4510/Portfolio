package jp.co.tk.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jp.co.tk.entity.BbsThreadEntity;


@Repository
public interface BbsThreadRepository extends JpaRepository<BbsThreadEntity, Integer>{


	@Query(value="select * from bbsthread where bbs_id = ?1 order by id desc"
			, nativeQuery = true)
	List<BbsThreadEntity> findByBbsId(int id);

	@Transactional
	@Modifying
	@Query(value = "delete from bbsthread where id = ?1"
			, nativeQuery = true)
	public int delete(String thread_id);

	//bbs編集IDを取得
	@Query(value = "select * from bbsthread where id = ?1"
			, nativeQuery = true)

	Optional<BbsThreadEntity> edit(String thread_id);


}
