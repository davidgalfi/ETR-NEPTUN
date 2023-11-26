package com.ETR.NEPTUN.room;

import com.ETR.NEPTUN.exam.Exam;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/rooms")
    public String roomsPage(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        } else {
            List<Room> rooms = roomService.getAllRooms();
            model.addAttribute("username", username);
            model.addAttribute("rooms", rooms);
            return "Rooms";
        }
    }
}
