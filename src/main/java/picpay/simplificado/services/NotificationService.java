package picpay.simplificado.services;

import jakarta.enterprise.context.ApplicationScoped;
import picpay.simplificado.entities.user.User;
import picpay.simplificado.dtos.NotificationDTO;
import picpay.simplificado.exceptions.GlobalException;


@ApplicationScoped
public class NotificationService {

    public void sendNotification(User user, String message) throws GlobalException {
        String email = user.getEmail();
        NotificationDTO notificationRequest = new NotificationDTO(email, message);

//        ResponseEntity<String> notificationResponse = restTemplate.postForEntity("https://util.devi.tools/api/v1/notify", notificationRequest, String.class);
//
//        if(!notificationResponse.getStatusCode().equals(HttpStatus.OK)){
//            throw new Exception("Erro ao enviar notificação");
//        }

        System.out.println("Notificação enviada com sucesso!");
    }
}
