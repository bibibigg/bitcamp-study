package bitcamp.myapp.service;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class DefaultMemberService implements MemberService {

  MemberDao memberDao;
  TransactionTemplate txTemplate;

  public DefaultMemberService(MemberDao memberDao, PlatformTransactionManager txManager) {
    this.memberDao = memberDao;
    this.txTemplate = new TransactionTemplate(txManager);
  }

  @Transactional
  @Override
  public int add(Member member) throws Exception {
    return txTemplate.execute(status -> memberDao.insert(member));
  }

  @Override
  public List<Member> list() throws Exception {
    return memberDao.findAll();
  }

  @Override
  public Member calculateEndDate(Member member) throws Exception {
    int inputMonths = member.getPer();
    LocalDate localCreatedDate = member.getCreatedDate().toLocalDate();
    LocalDate endDate = localCreatedDate.plusMonths(inputMonths);
    Date calculatedEndDate = Date.valueOf(endDate);
    LocalDate currentDate = LocalDate.now();
    long remainingDays = ChronoUnit.DAYS.between(currentDate, endDate);

    member.setCalculatedEndDate(calculatedEndDate);
    member.setRemainingDays(remainingDays);

    return member;
  }

  @Transactional
  @Override
  public Member get(int memberNo) throws Exception {
    return memberDao.findBy(memberNo);
  }

  @Override
  public Member get(String phoneNumber, String password) throws Exception {
    return memberDao.findByPhoneAndPassword(phoneNumber, password);
  }

  @Transactional
  @Override
  public int update(Member member) throws Exception {
    return txTemplate.execute(status -> memberDao.update(member));
  }

  @Transactional
  @Override
  public int delete(int memberNo) throws Exception {
    return txTemplate.execute(status -> memberDao.delete(memberNo));
  }
}
