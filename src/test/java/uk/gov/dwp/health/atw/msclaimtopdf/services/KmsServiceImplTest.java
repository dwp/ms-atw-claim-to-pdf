package uk.gov.dwp.health.atw.msclaimtopdf.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.dwp.health.atw.msclaimtopdf.services.impl.KmsServiceImpl;
import uk.gov.dwp.health.crypto.CryptoDataManager;
import uk.gov.dwp.health.crypto.CryptoMessage;
import uk.gov.dwp.health.crypto.exception.CryptoException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class KmsServiceImplTest {

  @Captor ArgumentCaptor<String> strArgCaptor;
  @Captor ArgumentCaptor<CryptoMessage> messageArgumentCaptor;
  @InjectMocks private KmsServiceImpl cut;
  @Mock private CryptoDataManager manager;

  @Test
  void testKmsEncryptionContent() throws CryptoException {
    byte[] content = "TEST".getBytes();
    CryptoMessage cryptoMessage = mock(CryptoMessage.class);
    when(manager.encrypt(anyString())).thenReturn(cryptoMessage);
    cut.encrypt(content);
    verify(manager).encrypt(strArgCaptor.capture());
    assertThat(strArgCaptor.getValue()).isEqualTo("VEVTVA==");
  }

  @Test
  void testKmsEncryptionContentThrowsCryptoException() throws CryptoException {
    byte[] content = "TEST".getBytes();
    when(manager.encrypt(anyString())).thenThrow(CryptoException.class);
    Assertions.assertThrows(CryptoException.class, () -> cut.encrypt(content));
    verify(manager).encrypt(strArgCaptor.capture());
    assertThat(strArgCaptor.getValue()).isEqualTo("VEVTVA==");
  }

  @Test
  void testKmsDecryptionContent() throws CryptoException {
    CryptoMessage cryptoMessage = mock(CryptoMessage.class);
    when(manager.decrypt(any(CryptoMessage.class))).thenReturn("aGVsbG8=");
    cut.decrypt(cryptoMessage);
    verify(manager).decrypt(messageArgumentCaptor.capture());
  }

  @Test
  void testKmsDecryptionContentThrowsCryptoException() throws CryptoException {
    CryptoMessage cryptoMessage = mock(CryptoMessage.class);
    when(manager.decrypt(any(CryptoMessage.class))).thenThrow(CryptoException.class);
    Assertions.assertThrows(CryptoException.class, () -> cut.decrypt(cryptoMessage));
    verify(manager).decrypt(messageArgumentCaptor.capture());
  }
}
