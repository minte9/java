/**
 * Plain Java without Spring
 * 
 * We crete dependencies manually with new 
 */

package com.example.manual_creation;

public class Main {
    public static void main(String[] args) {
        MessageService service = new MessageService();
        NotificationController controller = new NotificationController(service);

        controller.print();  // Hello from MessageService
    }
}

class MessageService {
    public String getMessage() {
        return "Hello from MessageService";
    }
}

class NotificationController {
    private final MessageService messageService;

    public NotificationController(MessageService messageService) {
        this.messageService = messageService;
    }

    public void print() {
        System.out.println(messageService.getMessage());
    }
}
