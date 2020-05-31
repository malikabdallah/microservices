package com.sha.microservicemuseemanagement.controller;

import com.sha.microservicemuseemanagement.helpers.QRCodeHelper;
import com.sha.microservicemuseemanagement.model.Billet;
import com.sha.microservicemuseemanagement.repository.BilletRepository;
import com.sha.microservicemuseemanagement.service.BilletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

@RestController
@RequestMapping("/qrcode")
public class QRCodeController {

    @Autowired
    private BilletService billetService;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    BilletRepository billetRepository;

    @Value("${courses.qrcode.privatekey}")
    private String privateKey;

    @Value("${courses.mail.from.email}")
    private String fromEmail;

    private String stringForDefaultQRCode = "Mon joli QR Code !";
    private static final int qrCodeWidth  = 200;
    private static final int qrCodeHeight = 200;

    public String sha256(String string) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
            byte[] array = md.digest(string.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    public void sendEmail(String to, String from, String sub, String msgBody, byte[] content) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(sub);
            helper.setText(msgBody);
            helper.addAttachment("Billet.png", new ByteArrayResource(content));
            javaMailSender.send(message);
        } catch (MessagingException e) {

            e.printStackTrace();
        }
    }

    // http://localhost:8001/qrcode
    @RequestMapping(method = RequestMethod.GET)
    public void index(HttpServletResponse response) throws Exception {
        response.setContentType("image/png");
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(QRCodeHelper.getQRCodeImage(stringForDefaultQRCode, qrCodeWidth, qrCodeWidth));
        outputStream.flush();
        outputStream.close();
    }

    // http://localhost:8001/qrcode/billet/1
    @RequestMapping(value = "billet/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getQRCodeByBilletIdWithoutEmail(@PathVariable("id") long id, HttpServletResponse response) throws Exception {
        // Récupération de l'ID du billet
        Billet billet = (Billet) billetService.getBilletById(id);
        if(billet != null) {
            String idBillet = String.valueOf(billet.getId());

            // Génération du token du QR Code
            String token = sha256(idBillet + privateKey);

            // Préparation de la chaine présente dans le QR Code
            // API : http://localhost:8765/api/musee/qrcode/billet/1/validate/1bd78c77897a3d4d12a50fdbfd612e3ade5641168ea7620c990d74308331438b
            String qrCodeString = "http://localhost:8765/api/musee/qrcode/billet/" + idBillet + "/validate/" + token;

            // Génération du QR Code
            response.setContentType("image/png");
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(QRCodeHelper.getQRCodeImage(qrCodeString, qrCodeWidth, qrCodeWidth));
            outputStream.flush();
            outputStream.close();

            // On retourne OK pour dire que tout s'est bien passé
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            // Billet trop trouvé
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // http://localhost:8001/qrcode/billet/1/send/xxx@yyy.tld
    @RequestMapping(value = "billet/{id}/send/{email}", method = RequestMethod.GET)
    public ResponseEntity<Object> getQRCodeByBilletId(@PathVariable("id") long id, @PathVariable("email") String email, HttpServletResponse response) throws Exception {
        // Récupération de l'ID du billet
        Billet billet = (Billet) billetService.getBilletById(id);
        if(billet != null) {
            String idBillet = String.valueOf(billet.getId());

            // Génération du token du QR Code
            String token = sha256(idBillet + privateKey);

            // Préparation de la chaine présente dans le QR Code
            // API : http://localhost:8765/api/musee/qrcode/billet/1/validate/1bd78c77897a3d4d12a50fdbfd612e3ade5641168ea7620c990d74308331438b
            String qrCodeString = "http://localhost:8765/api/musee/qrcode/billet/" + idBillet + "/validate/" + token;

            // Génération du QR Code
            response.setContentType("image/png");
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(QRCodeHelper.getQRCodeImage(qrCodeString, qrCodeWidth, qrCodeWidth));
            outputStream.flush();
            outputStream.close();

            // Envoi du mail
            if(!email.isEmpty()) {
                byte[] content = QRCodeHelper.getQRCodeImage(qrCodeString, qrCodeWidth, qrCodeWidth);
                sendEmail(email, fromEmail, "Votre billet d'accès au musée", "Bonjour, vous trouverez en ci-joint votre billet.", content);
            }

            // On retourne OK pour dire que tout s'est bien passé
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            // Billet trop trouvé
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // http://localhost:8001/qrcode/billet/1/validate/1bd78c77897a3d4d12a50fdbfd612e3ade5641168ea7620c990d74308331438b
    // Pour le QR Code : http://localhost:8765/api/musee/qrcode/billet/1/validate/1bd78c77897a3d4d12a50fdbfd612e3ade5641168ea7620c990d74308331438b
    @RequestMapping(value = "billet/{id}/validate/{qrCodeToken}", method = RequestMethod.GET)
    public ResponseEntity<Billet> validateBIllet(@PathVariable("id") long id, @PathVariable("qrCodeToken") String qrCodeToken, HttpServletResponse response) throws Exception {
        String calculateToken = sha256(String.valueOf(id + privateKey));
        // Token OK
        if(qrCodeToken.equals(calculateToken)) {
            Billet billet = billetService.getBilletById(id);
            if(billet != null) {
                if (billet.getUtilise() == false) {
                    billet.setUtilise(true);
                    billetRepository.save(billet);
                    return ResponseEntity.ok(billet);
                } else {
                    return new ResponseEntity<>(null, HttpStatus.IM_USED);
                }
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }

        // Token ERROR
        else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

}