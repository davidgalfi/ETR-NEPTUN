package com.ETR.NEPTUN.user;

import com.ETR.NEPTUN.addcourse.AddCourse;
import com.ETR.NEPTUN.addcourse.AddCourseService;
import com.ETR.NEPTUN.addexam.AddExam;
import com.ETR.NEPTUN.addexam.AddExamService;
import com.ETR.NEPTUN.course.Course;
import com.ETR.NEPTUN.course.CourseService;
import com.ETR.NEPTUN.course.dto.RegisterCourse;
import com.ETR.NEPTUN.exam.Exam;
import com.ETR.NEPTUN.exam.ExamService;
import com.ETR.NEPTUN.exam.dto.RegisterExam;
import com.ETR.NEPTUN.room.Room;
import com.ETR.NEPTUN.room.RoomService;
import com.ETR.NEPTUN.user.dto.RegisterUser;
import com.ETR.NEPTUN.user.dto.UserDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Controller
public class UserController {

    private final UserService userService;
    private final AddCourseService addCourseService;
    private final AddExamService addExamService;
    private final CourseService courseService;
    private final ExamService examService;
    private final RoomService roomService;

    @Autowired
    public UserController(UserService userService, AddCourseService addCourseService, AddExamService addExamService, CourseService courseService, ExamService examService, RoomService roomService) {
        this.userService = userService;
        this.addCourseService = addCourseService;
        this.addExamService = addExamService;
        this.courseService = courseService;
        this.examService = examService;
        this.roomService = roomService;
    }

    //Login page
    @GetMapping("/login")
    public String loginPage() {
        return "Login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session, Model model) {
        if (userService.isValidUser(username, password)) {
            session.setAttribute("username", username);
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Hibás felhasználónév vagy jelszó");
            return "Login";
        }
    }


    @GetMapping("/register")
    public String registerPage() {
        return "Register";
    }
    @PostMapping("/register")
    public String register(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("doBorn") String doBorn,
                           @RequestParam("doBirth") LocalDate doBirth,
                           @RequestParam("firstName") String firstName,
                           @RequestParam("lastName") String lastName,
                           @RequestParam("status") String status,
                           @RequestParam("major") String major,
                           Model model) {
        if (userService.isUserExists(username)) {
            model.addAttribute("error", "A felhasználónév már foglalt!");
            return "Register";
        }

        RegisterUser newUser = new RegisterUser(username, password, doBorn, doBirth, firstName, lastName, status, major);
        userService.registerUser(newUser);

        model.addAttribute("success", "Sikeres regisztráció!");
        return "Register";
    }

    @GetMapping("/dashboard")
    public String dashboardPage(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        } else {
            model.addAttribute("username", username);
            return "Dashboard";
        }
    }

    @PostMapping("/enroll-course/{courseId}")
    public String enrollCourse(@PathVariable("courseId") Long courseId,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        } else {
            User user = userService.findWholeByUsername(username);
            if(courseService.isTeacherOwnTheCourse(username, courseId)) {
                redirectAttributes.addFlashAttribute("error", "Saját kurzust nem vehet fel!");
                return "redirect:/courses";
            }
            if(addCourseService.existByUsername(username, courseId)) {
                redirectAttributes.addFlashAttribute("error", "A kurzust már felvetted!");
                return "redirect:/courses";
            }
            if(addCourseService.isCourseFilled(courseId)) {
                redirectAttributes.addFlashAttribute("error", "A Kurzus betelt!");
                return "redirect:/courses";
            }
            redirectAttributes.addFlashAttribute("success_enroll","Sikeres felvétel!");
            Course course = courseService.findByCourseId(courseId);
            course.setStudentsNumber(course.getStudentsNumber() + 1);
            addCourseService.saveCourse(course, user);
            return"redirect:/courses";
        }
    }

    @PostMapping("/drop-course/{courseId}")
    public String dropCourse(@PathVariable("courseId") Long courseId,
                             HttpSession session,
                             RedirectAttributes redirectAttributes) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        } else {
            if(!addCourseService.existByUsername(username, courseId)) {
                redirectAttributes.addFlashAttribute("error", "A kurzus még nincs felvéve!");
                return "redirect:/courses";
            }
            redirectAttributes.addFlashAttribute("success_drop","Sikeres leadás!");
            Course course = courseService.findByCourseId(courseId);
            course.setStudentsNumber(course.getStudentsNumber() - 1);
            addCourseService.deleteAddCourse(courseId, username);
            return"redirect:/courses";
        }
    }

    @PostMapping("/enroll-exam/{examId}")
    public String enrollExam(@PathVariable("examId") Long examId,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        } else {
            User user = userService.findWholeByUsername(username);
            if(examService.isTeacherOwnTheExam(username, examId)) {
                redirectAttributes.addFlashAttribute("error", "Saját vizsgát nem vehet fel!");
                return "redirect:/exams";
            }
            if(addExamService.existByUsername(username, examId)) {
                redirectAttributes.addFlashAttribute("error", "A vizsgát már felvetted!");
                return "redirect:/exams";
            }
            if(addExamService.isExamFilled(examId)) {
                redirectAttributes.addFlashAttribute("error", "A vizsga már betelt!");
                return "redirect:/exams";
            }
            redirectAttributes.addFlashAttribute("success_enroll","Sikeres felvétel!");
            Exam exam = examService.findByExamId(examId);
            exam.setStudentsNumber(exam.getStudentsNumber() + 1);
            addExamService.saveExam(exam, user);
            return"redirect:/exams";
        }
    }

    @PostMapping("/drop-exam/{examId}")
    public String dropExam(@PathVariable("examId") Long examId,
                             HttpSession session,
                             RedirectAttributes redirectAttributes) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        } else {
            if(!addExamService.existByUsername(username, examId)) {
                redirectAttributes.addFlashAttribute("error", "A vizsgát nem vetted még fel!");
                return "redirect:/exams";
            }
            redirectAttributes.addFlashAttribute("success_drop","Sikeres leadás!");
            Exam exam = examService.findByExamId(examId);
            exam.setStudentsNumber(exam.getStudentsNumber() - 1);
            addExamService.deleteAddExam(examId, username);
            return"redirect:/exams";
        }
    }

    @PostMapping("/delete-course/{courseId}")
    public String deleteExam(@PathVariable("courseId") Long courseId,
                           HttpSession session,
                           RedirectAttributes redirectAttributes) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        } else {

            addCourseService.deleteAllAddCourseById(courseId);

            courseService.deleteCourse(courseId);
            redirectAttributes.addFlashAttribute("success_delete","Sikeres törlés!");
            return"redirect:/profile";
        }
    }

    @GetMapping("/profile")
    public String profilePage(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        } else {
            UserDTO user = userService.findByUsername(username);
            List<AddCourse> addedCourses = addCourseService.getCoursesForUser(username);
            List<AddExam> addedExams = addExamService.getExamsForUser(username);
            model.addAttribute("user", user);
            model.addAttribute("addedCourses", addedCourses);
            model.addAttribute("addedExams", addedExams);
            if (user.status().equals("Oktató")) {
                List<Room> rooms = roomService.getAllRooms();
                List<Course> myCourses = courseService.getMyCourses(username);
                List<Exam> myExams = examService.getMyExams(username);
                model.addAttribute("user", user);
                model.addAttribute("rooms", rooms);
                model.addAttribute("myCourses", myCourses);
                model.addAttribute("myExams", myExams);
                return "Profile";
            }
            return "Profile";
        }
    }

    @PostMapping("/course-registration")
    public String courseRegistration(
            @RequestParam("title") String title,
            @RequestParam("capacity") Integer capacity,
            @RequestParam("type") String type,
            @RequestParam("semester") String semester,
            @RequestParam("weeklyHours") Integer weeklyHours,
            @RequestParam("roomId") Long roomId,
            RedirectAttributes redirectAttributes, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if(username == null) {
            return "redirect:/login";
        }
        Room room = roomService.findByRoomId(roomId);
        User user = userService.findWholeByUsername(username);
        if(room.getCapacity() < capacity){
            redirectAttributes.addFlashAttribute("error_course",
                    "Nem haladhatja meg a terem befogadóképességét: " + room.getCapacity() + " főt");
            return "redirect:/profile";
        }

        RegisterCourse newCourse = new RegisterCourse(
                title,
                capacity,
                type,
                semester,
                weeklyHours);
        courseService.registerCourse(newCourse, room, user);

        redirectAttributes.addFlashAttribute("success_course", "Sikeres a kurzus létrehozása!");
        return "redirect:/profile";
    }

    @PostMapping("/exam-registration")
    public String examRegistration(
            @RequestParam("typeE") String typeE,
            @RequestParam("capacityE") Integer capacityE,
            @RequestParam("dateTime") LocalDateTime dateTime,
            @RequestParam("courseId") Long courseId,
            @RequestParam("roomIdE") Long roomIdE,
            RedirectAttributes redirectAttributes, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if(username == null) {
            return "redirect:/login";
        }
        Room room = roomService.findByRoomId(roomIdE);
        Course course = courseService.findByCourseId(courseId);
        if(room.getCapacity() < capacityE){
            redirectAttributes.addFlashAttribute("error_exam",
                    "Nem haladhatja meg a terem befogadóképességét: " + room.getCapacity() + " főt");
            return "redirect:/profile";
        }
        if(examService.existByIdAndRoomId(dateTime, roomIdE)){
            redirectAttributes.addFlashAttribute("error_exam",
                    "Egy vizsgát már regisztráltak ebben a teremben és időpontban!");
            return "redirect:/profile";
        }

        RegisterExam newExam = new RegisterExam(
                typeE,
                capacityE,
                dateTime
                );

        examService.registerExam(newExam, course,room);

        redirectAttributes.addFlashAttribute("success_exam", "Sikeres a vizsga létrehozása!");
        return "redirect:/profile";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, Model model)   {
        if(session.getAttribute("username") == null){
            return "redirect:/login";
        }
        session.removeAttribute("username");
        model.addAttribute("success","A kijelentkezés sikeres volt!");
        return "Login";
    }
}
