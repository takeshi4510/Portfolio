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


	//BBSTHREADTABLEからIDを元にデータを取得
	@Query(value="select * from BBSTHREAD where BBS_ID = ?1 order by id desc"
			, nativeQuery = true)
	List<BbsThreadEntity> findByBbsId(int id);

	//IDをもとにBBSTHREADTABLEからデータを削除
	@Transactional
	@Modifying
	@Query(value = "delete from BBSTHREAD where ID = ?1"
	, nativeQuery = true)
	public int deleteBbsThread(String thread_id);

	//IDをもとにBBSTHREADTABLEからデータを取得
	@Query(value = "select * from BBSTHREAD where ID = ?1"
			, nativeQuery = true)

	Optional<BbsThreadEntity> editBbsThread(String thread_id);

}
