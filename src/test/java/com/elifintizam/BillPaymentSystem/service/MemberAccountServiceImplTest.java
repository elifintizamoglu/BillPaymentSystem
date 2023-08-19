package com.elifintizam.BillPaymentSystem.service;
import com.elifintizam.BillPaymentSystem.model.MemberAccount;
import com.elifintizam.BillPaymentSystem.repository.MemberAccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MemberAccountServiceImplTest {

    @InjectMocks
    private MemberAccountServiceImpl memberAccountServiceImpl;

    @Mock
    private MemberAccountRepository memberAccountRepository;

    @Test
    void testGetMembers() {
    }

    @Test
    void testPostMember() {
        MemberAccount memberAccount = new MemberAccount(1,"Test-FirstName","Test-LastName",200);
        memberAccountServiceImpl.postMember(memberAccount);
    }

    @Test
    void testDeleteMember() {
    }

    @Test
    void testUpdateMember() {
    }
}