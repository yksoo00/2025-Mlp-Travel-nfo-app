package com.multi.travelapp.view;


import com.multi.travelapp.common.Session;
import com.multi.travelapp.controller.*;
import com.multi.travelapp.model.dto.MemberDto;
import com.multi.travelapp.model.dto.ReviewDto;
import com.multi.travelapp.model.dto.TouristSpotDto;
import com.multi.travelapp.model.dto.SignInDto;
import com.multi.travelapp.service.LikesService;

import java.util.*;

import static java.lang.System.exit;

public class TravelView {

    private Scanner sc = new Scanner(System.in);
    //private TravelController travelController = new TravelController();
    private BookMarkController bookMarkController = new BookMarkController();
    private LikesController likesController = new LikesController();
    private MemberController memberController = new MemberController();
    private ReviewController reviewController = new ReviewController();
    private TouristSpotController touristSpotController = new TouristSpotController();


    public TravelView() {
        this.likesController = new LikesController(this);
        this.bookMarkController = new BookMarkController(this);

    }


    public void firstPage() {
        while (true) {
            System.out.println();
            System.out.println("---로그인/회원가입 화면---");
            System.out.println("1. 로그인");
            System.out.println("2. 회원가입");
            System.out.println("9. Travel App 종료");
            System.out.print("입력 : ");
            int input = Integer.parseInt(sc.nextLine());
            switch (input) {
                case 1:
                    signInPage();
                    break;
                case 2:
                    signUpPage(); // 회원가입 화면
                    break;
                case 9:
                    exit(0);
                    break;
                default:
                    System.out.println("올바른 값을 입력해주세요");
                    break;
            }
        }

    }

    public void signInPage() {
        while (true) {
            System.out.println();
            System.out.println("---로그인 화면---");
            System.out.println("1. 로그인");
            System.out.println("9. 로그인/회원가입 화면으로 이동");
            System.out.print("입력 : ");
            int input = Integer.parseInt(sc.nextLine());

            switch (input) {
                case 1:
                    System.out.print("이메일 입력 : "); // 이메일 입력
                    String email = sc.nextLine();

                    System.out.print("비밀번호 입력 : "); // 비밀번호 입력
                    String password = sc.nextLine();

                    SignInDto signInDto = new SignInDto(email, password);

                    MemberDto memberDto = memberController.signIn(signInDto);

                    if (memberDto != null) {
                        Session.login(memberDto);
                        isAdmin(memberDto);
                        if (Session.getIsAdmin()) {
                            adminPage();
                        }
                        customerMainPage();
                    } // 로그인
                    else return;


                    break;
                case 9:
                    return;
                default:
                    System.out.println("올바른 값을 입력해주세요");
                    break;
            }


        }
    }

    public void signUpPage() {
        while (true) {
            System.out.println();
            System.out.println("---회원가입 화면---");
            System.out.println("1. 회원가입");
            System.out.println("9. 로그인/회원가입 화면으로 이동");
            System.out.print("입력 : ");
            String input = sc.nextLine();

            switch (input) {
                case "1":
                    System.out.print("이름 입력 : ");
                    String name = sc.nextLine();
                    System.out.print("이메일 입력 : ");
                    String email = sc.nextLine();
                    System.out.print("비밀번호 입력 : ");
                    String password = sc.nextLine();
                    System.out.print("휴대폰 번호 : ");
                    String phone = sc.nextLine();
                    System.out.print("주소 입력 : ");
                    String address = sc.nextLine();

                    MemberDto memberDto = new MemberDto();
                    memberDto.setName(name);
                    memberDto.setEmail(email);
                    memberDto.setPassword(password);
                    memberDto.setPhone(phone);
                    memberDto.setAddress(address);

                    boolean result = memberController.signUp(memberDto);
                    if (result) {
                        System.out.println("로그인/회원가입 화면으로 이동");
                        return;
                    }

                    break;
                case "9":
                    return;
                default:
                    System.out.println("올바른 값을 입력하세요");
                    break;
            }
        }

    }


    public void customerMainPage() {
        Long memberId = Session.getCurrentMemberId();
        while (true) {
            System.out.println();
            System.out.println("---고객 메인 화면---");
            System.out.println("1. 관광지 조회 방법 선택");
            System.out.println("2. 내 관광지 리뷰 조회, 수정, 삭제");
            System.out.println("3. 내 즐겨찾기 조회, 삭제");
            System.out.println("9. 로그아웃 - 로그인 화면으로 이동");
            System.out.print("입력 : ");
            int input = Integer.parseInt(sc.nextLine());

            switch (input) {
                case 1:
                    touristSpotPage(); // 관광지 목록 조회 화면
                    break;
                case 2:
                    myReviewPage(); // 내 리뷰 화면
                    break;
                case 3:
                    myBookMarkPage(memberId); // 즐겨찾기 화면
                    break;
                case 9:
                    Session.logout();
                    return; // 로그아웃 - 로그인 화면으로 이동
                default:
                    System.out.println("올바른 값을 입력하세요");
                    break;
            }
        }
    }


    // ------------------------------- 관광지 서비스 ------------------------------
    // 관광지 조회 방법 선택 화면

    public void touristSpotPage() {
        Long memberId = Session.getCurrentMemberId();
        boolean isAdmin = Session.getIsAdmin();
        while (true) {
            System.out.println();
            System.out.println("---관광지 조회 방법 선택 화면---");
            System.out.println("1. 관광지 전체 조회(페이지별)");
            System.out.println("2. 관광지 제목으로 조회");
            System.out.println("3. 권역별 관광지 조회");
            System.out.println("4. 좋아요 순으로 조회");
            if (isAdmin) {
                System.out.println("9. 관리자 페이지로 이동");
            } else {
                System.out.println("9. 메인 화면으로 이동");
            }
            System.out.print("입력 : ");
            int input = Integer.parseInt(sc.nextLine());

            switch (input) {
                case 1:
                    allTouristSpotPage(); // 관광지 전체 조회(페이지별)
                    break;
                case 2:
                    byTitleTouristSpotPage(); // 관광지 제목으로 조회
                    break;
                case 3:
                    byRegionTouristSpotPage(); // 권역별 관광지 조회
                    break;
                case 4:
                    byLikesTouristSpotPage(); // 좋아요 순으로 조회
                    break;
                case 9:
                    return; // 메인 화면으로
                default:
                    System.out.println("올바른 값을 입력하세요");
                    break;
            }
        }
    }

    // 관광지 전체 조회(페이지별)

    public void allTouristSpotPage() {
        Long memberId = Session.getCurrentMemberId();
        while (true) {
            System.out.println();
            System.out.println("---관광지 전체 페이지별 조회 화면---");

            System.out.println("1. 페이지별 관광지 조회");
            System.out.println("2. 상세 정보 보기");
            System.out.println("9. 관광지 조회 방법 선택 화면으로 이동");
            System.out.print("입력 : ");
            int input = Integer.parseInt(sc.nextLine());

            switch (input) {
                case 1:
                    System.out.print("조회할 페이지를 입력하세요 : ");
                    int page = Integer.parseInt(sc.nextLine());
                    touristSpotController.selectAllTouristSpotByPage((page - 1) * 10);

                    break;
                case 2:
                    System.out.print("(상세 정보 보기) 관광지 ID 입력 : ");
                    Long touristSpotId = Long.parseLong(sc.nextLine()); // 사용자가 touristSpotId 입력
                    ArrayList<TouristSpotDto> list = touristSpotController.selectTouristSpotById(memberId, touristSpotId);

                    break;
                case 9:
                    return;
                default:
                    System.out.println("올바른 값을 입력하세요");
                    break;
            }
        }
    }

    // 관광지 제목으로 조회

    public void byTitleTouristSpotPage() {
        Long memberId = Session.getCurrentMemberId();
        while (true) {
            System.out.println();
            System.out.println("---관광지 제목으로 조회 화면---");
            System.out.println("1. 관광지 제목으로 조회");
            System.out.println("2. 상세 정보 보기");
            System.out.println("9. 관광지 조회 방법 선택 화면으로 이동");
            System.out.print("입력 : ");
            int input = Integer.parseInt(sc.nextLine());

            switch (input) {
                case 1:
                    System.out.print("관광지 제목 입력 : ");

                    String title = sc.nextLine();
                    touristSpotController.selectTouristSpotByTitle(memberId, title);
                    break;
                case 2:
                    System.out.println("(상세 정보 보기) 관광지 ID 입력 : ");
                    Long touristSpotId = Long.parseLong(sc.nextLine());
                    ArrayList<TouristSpotDto> list = touristSpotController.selectTouristSpotById(memberId, touristSpotId);
                    //detailPage(touristSpotId,list);
                    break;
                case 9:
                    return;
                default:
                    System.out.println("올바른 값을 입력하세요");
                    break;
            }

        }
    }


    // 권역별 관광지 조회
    public void byRegionTouristSpotPage() {
        Long memberId = Session.getCurrentMemberId();
        while (true) {

            System.out.println();
            System.out.println("---권역별 관광지 조회 화면---");
            System.out.println("1. 권역별 관광지 조회");
            System.out.println("2. 상세 정보 보기");
            System.out.println("9. 관광지 조회 방법 선택 화면으로 이동");
            System.out.print("입력 : ");
            int input = Integer.parseInt(sc.nextLine());

            switch (input) {
                case 1:
                    System.out.print("관광지 권역 입력 : ");
                    String district = sc.nextLine();
                    touristSpotController.selectTouristSpotByDistrict(memberId, district);
                    break;
                case 2:
                    System.out.println("(상세 정보 보기) 관광지 ID 입력 : ");
                    Long touristSpotId = Long.parseLong(sc.nextLine());
                    ArrayList<TouristSpotDto> list = touristSpotController.selectTouristSpotById(memberId, touristSpotId);
                    //detailPage(touristSpotId,list);
                    break;
                case 9:
                    return;
                default:
                    System.out.println("올바른 값을 입력하세요");
                    break;
            }
        }
    }


    public void byLikesTouristSpotPage() {
        Long memberId = Session.getCurrentMemberId();
        while (true) {
            System.out.println();
            System.out.println("---좋아요 순으로 조회 화면---");
            System.out.println("1. 좋아요 순으로 관광지 조회");
            System.out.println("2. 상세 정보 보기");
            System.out.println("9. 관광지 조회 방법 선택 화면으로 이동");
            System.out.print("입력 : ");
            int input = Integer.parseInt(sc.nextLine());

            switch (input) {
                case 1:
                    likesController.selectAllTouristSpotOrderByLikes();
                    break;
                case 2:
                    System.out.print("(상세 정보 보기) 관광지 ID 입력 : ");
                    Long touristSpotId = Long.parseLong(sc.nextLine());
                    ArrayList<TouristSpotDto> list = touristSpotController.selectTouristSpotById(memberId, touristSpotId);
                    //detailPage(touristSpotId,list);
                    break;
                case 9:
                    return;
                default:
                    System.out.println("올바른 값을 입력하세요");
                    break;
            }
        }
    }


    // 상세 정보 보기 화면
    public void detailPage(Long touristSpotId, ArrayList<TouristSpotDto> list) {
        LikesService likesService = new LikesService();
        Long memberId = Session.getCurrentMemberId();
        while (true) {
            System.out.println();
            System.out.println("---{관광지 id : " + touristSpotId + "}의 상세 정보 보기 화면---"); // 상세페이지에서 관광지id볼 수 있도록 수정
            //touristSpotController.selectTouristSpotById(memberId, touristSpotId); // 관광지 정보 출력

            for (TouristSpotDto touristSpotDto : list) {
                System.out.println("관광지 ID\t: " + touristSpotDto.getTourist_spot_id());
                System.out.println("제목\t\t\t: " + touristSpotDto.getTitle());
                System.out.println("권역\t\t\t: " + touristSpotDto.getDistrict());
                System.out.println("설명\t\t\t: " + touristSpotDto.getDescription());
                System.out.println("주소\t\t\t: " + touristSpotDto.getAddress());
                System.out.println("전화번호\t\t: " + touristSpotDto.getPhone());
            }

            // 좋아요 개수 조회 및 출력
            int likeCount = likesService.getLikeCount(touristSpotId);
            System.out.println("❤️ 좋아요 수 : " + likeCount);


            System.out.println("-------------------------------------");

            reviewController.selectAllReviewByTouristSpotId(memberId, touristSpotId); // 리뷰 출력

            System.out.println("1. 리뷰 등록");
            System.out.println("2. 리뷰 삭제");
            System.out.println("3. 즐겨찾기 등록/삭제");
            System.out.println("4. 좋아요 등록/삭제");
            System.out.println("9. 이전 화면으로");
            System.out.print("입력 : ");
            String input = sc.nextLine();

            switch (input) {
                case "1": // 리뷰 등록
                    System.out.print("리뷰 제목 입력 : ");
                    String title = sc.nextLine();
                    System.out.print("리뷰 내용 입력 : ");
                    String description = sc.nextLine();
                    System.out.print("리뷰 점수 입력(0~5) : ");
                    int score = Integer.parseInt(sc.nextLine());

                    ReviewDto reviewDto = new ReviewDto();
                    reviewDto.setTitle(title);
                    reviewDto.setDescription(description);
                    reviewDto.setScore(score);
                    reviewDto.setMemberId(memberId);
                    reviewDto.setTouristSpotId(touristSpotId);

                    reviewController.insertReview(reviewDto);
                    break;
                case "2": // 리뷰 삭제
                    System.out.print("삭제할 리뷰의 ID를 입력하세요 : ");
                    Long reviewId = Long.parseLong(sc.nextLine());
                    reviewController.deleteReview(memberId, reviewId);
                    break;
                case "3": // 즐겨찾기 등록/삭제
                    bookMarkController.updateBookMark(memberId, touristSpotId);
                    break;
                case "4": // 좋아요 등록/삭제
                    likesController.updateLikes(memberId, touristSpotId);
                    break;
                case "9": // 이전 화면으로
                    return;
                default:
                    System.out.println("올바른 값을 입력하세요");
                    break;
            }
        }
    }


    public void adminPage() {
        while (true) {
            System.out.println();
            System.out.println("---관리자 화면---");
            System.out.println("1. 관광지 조회 방법 선택");
            System.out.println("2. 관광지 등록");
            System.out.println("3. 관광지 수정");
            System.out.println("4. 관광지 삭제");
            System.out.println("9. 로그아웃 - 로그인 화면으로 이동");
            System.out.print("입력 : ");
            int input = Integer.parseInt(sc.nextLine());

            switch (input) {
                case 1:
                    touristSpotPage(); // 관광지 목록 조회 화면
                    break;
                case 2:
                    touristSpotInsertPage();
                    break;
                case 3:
                    touristSpotUpdatePage();
                    break;
                case 4:
                    touristSpotdeletePage();
                    break;
                case 9:
                    Session.logout();
                    firstPage();
                    break;
                default:
                    System.out.println("올바른 값을 입력하세요");
                    break;
            }

        }
    }

    private void touristSpotdeletePage() {
        System.out.println();
        touristSpotController.selectTouristSpot();
        System.out.println("삭제할 관광지를 선택 : ");
        Long input = Long.parseLong(sc.nextLine());
        touristSpotController.deleteTouristSpot(input);

    }

    private void touristSpotUpdatePage() {
        System.out.println();
        touristSpotController.selectTouristSpot();
        System.out.println("수정할 관광지를 선택 : ");
        Long input = Long.parseLong(sc.nextLine());
        System.out.print("구역 입력 : ");
        String district = sc.nextLine();
        System.out.print("관광지 이름 입력 : ");
        String title = sc.nextLine();
        System.out.print("설명 입력 : ");
        String description = sc.nextLine();
        System.out.print("주소 입력 : ");
        String address = sc.nextLine();
        System.out.print("전화번호 입력 : ");
        String phone = sc.nextLine();
        TouristSpotDto touristSpotDto = new TouristSpotDto(district, title, description, address, phone);
        touristSpotController.UpdateTouristSpot(touristSpotDto, input);


    }

    private void touristSpotInsertPage() {
        System.out.println();
        System.out.print("구역 입력 : ");
        String district = sc.nextLine();
        System.out.print("관광지 이름 입력 : ");
        String title = sc.nextLine();
        System.out.print("설명 입력 : ");
        String description = sc.nextLine();
        System.out.print("주소 입력 : ");
        String address = sc.nextLine();
        System.out.print("전화번호 입력 : ");
        String phone = sc.nextLine();
        TouristSpotDto touristSpotDto = new TouristSpotDto(district, title, description, address, phone);
        touristSpotController.insertTouristSpot(touristSpotDto);
    }


    // 관광지 정보 출력
    public void displayTouristSpotList(ArrayList<TouristSpotDto> list) {
        for (TouristSpotDto touristSpotDto : list) {
            System.out.println("관광지 ID\t: " + touristSpotDto.getTourist_spot_id());
            System.out.println("제목\t\t\t: " + touristSpotDto.getTitle());
            System.out.println("권역\t\t\t: " + touristSpotDto.getDistrict());
            System.out.println("설명\t\t\t: " + touristSpotDto.getDescription());
            System.out.println("주소\t\t\t: " + touristSpotDto.getAddress());
            System.out.println("전화번호\t\t: " + touristSpotDto.getPhone());
            System.out.println("-------------------------------------");

        }
    }


    // ------------------------------- 리뷰 서비스 ------------------------------
    // 내 리뷰 화면

    public void myReviewPage() {
        Long memberId = Session.getCurrentMemberId();
        while (true) {
            System.out.println();
            System.out.println("---내 리뷰 화면---");
            System.out.println("---내 리뷰 목록---");
            reviewController.selectAllReviewByMemberId(memberId);

            System.out.println("1. 리뷰 삭제");
            System.out.println("2. 리뷰 수정");
            System.out.println("9. 메인 화면으로");

            System.out.print("입력 : ");
            int input = Integer.parseInt(sc.nextLine());
            Long reviewId;

            switch (input) {
                case 1: // 리뷰 삭제
                    System.out.print("삭제할 리뷰의 ID 입력 : ");
                    reviewId = Long.parseLong(sc.nextLine());
                    reviewController.deleteReview(memberId, reviewId);
                    break;
                case 2: // 리뷰 수정
                    System.out.print("수정할 리뷰의 ID 입력 : ");
                    reviewId = Long.parseLong(sc.nextLine());
                    updateReviewPage(reviewId);
                    break;
                case 9: // 메인 화면으로
                    return;
                default:
                    System.out.println("올바른 값을 입력하세요");
                    break;

            }
        }
    }

    // 리뷰 수정 화면
    public void updateReviewPage(Long reviewId) {
        Long memberId = Session.getCurrentMemberId();
        while (true) {

            System.out.println();
            System.out.println("---" + reviewId + "번 리뷰 수정 화면---");
            System.out.println("1. 리뷰 수정");
            System.out.println("9. 내 리뷰 화면으로 이동");
            System.out.print("입력 : ");
            int input = Integer.parseInt(sc.nextLine());

            switch (input) {
                case 1:
                    System.out.print("수정할 제목 입력 : ");
                    String title = sc.nextLine();
                    System.out.print("수정할 내용 입력 : ");
                    String description = sc.nextLine();
                    System.out.print("수정할 별점 입력(0~5) : ");
                    int score = Integer.parseInt(sc.nextLine());

                    reviewController.updateReview(memberId, reviewId, title, description, score);
                    break;
                case 9:
                    return;
                default:
                    System.out.println("올바른 값을 입력해주세요");
                    break;
            }


        }
    }

    // 리뷰 리스트 보여주기
    public void displayReviewList(ArrayList<ReviewDto> list) {
        for (ReviewDto reviewDto : list) {
            System.out.println("리뷰 ID\t\t: " + reviewDto.getReviewId() +
                    "\n사용자 ID\t: " + reviewDto.getMemberId() +
                    "\n별점\t\t\t: " + reviewDto.getScore() +
                    "\n제목\t\t\t: " + reviewDto.getTitle() +
                    "\n내용\t\t\t: " + reviewDto.getDescription() +
                    "\n관광지 ID\t: " + reviewDto.getTouristSpotId()
            );
            System.out.println("-------------------------------------");
        }
    }

    public void displaySuccess(String message) {
        System.out.println("성공! : " + message);
    }

    public void displayFail(String message) {
        System.out.println("실패ㅠㅠ : " + message);
    }

    public void displayNoData() {
        System.out.println("조회된 결과가 없습니다");
    }


    public void displayRegionPlace(ArrayList<TouristSpotDto> list) {
        System.out.println("\n조회된 정보는 다음과 같습니다.");
        System.out.println("----------------------------------------------------------");

        for (TouristSpotDto touristSpotDto : list) {
            System.out.println("관광지 ID\t: " + touristSpotDto.getTourist_spot_id());
            System.out.println("제목\t\t\t: " + touristSpotDto.getTitle());
            System.out.println("권역\t\t\t: " + touristSpotDto.getDistrict());
            System.out.println("설명\t\t\t: " + touristSpotDto.getDescription());
            System.out.println("주소\t\t\t: " + touristSpotDto.getAddress());
            System.out.println("전화번호\t\t: " + touristSpotDto.getPhone());
            System.out.println("-------------------------------------");
        }
    }

    public void displayRegionTitle(ArrayList<TouristSpotDto> list) {
        System.out.println("\n----- 관광지 제목 조회 결과 -----");
        for (TouristSpotDto touristSpotDto : list) {
            System.out.println("관광지 ID\t: " + touristSpotDto.getTourist_spot_id());
            System.out.println("제목\t\t\t: " + touristSpotDto.getTitle());
            System.out.println("권역\t\t\t: " + touristSpotDto.getDistrict());
            System.out.println("설명\t\t\t: " + touristSpotDto.getDescription());
            System.out.println("주소\t\t\t: " + touristSpotDto.getAddress());
            System.out.println("전화번호\t\t: " + touristSpotDto.getPhone());
            System.out.println("-------------------------------------");
        }
    }


    // ------------------------------- 즐겨찾기 서비스 ------------------------------
    // 내 즐겨찾기 화면
    public void myBookMarkPage(Long memberId) {
        while (true) {
            System.out.println();
            System.out.println("---내 즐겨찾기 화면---");
            System.out.println("---내 즐겨찾기 목록---");

            //즐겨찾기한 개수
            bookMarkController.showBookmarkCount(memberId);

            //즐겨찾기 한 목록
            bookMarkController.selectAllMyBookMarkPage(memberId);

            System.out.println("1. 상세 정보 보기");
            System.out.println("9. 메인 화면으로");

            System.out.print("입력 : ");
            int input = Integer.parseInt(sc.nextLine());

            switch (input) {
                case 1: // 상세 조회
                    System.out.println("(상세 정보 보기) 관광지 ID 입력 : ");
                    Long touristSpotId = Long.parseLong(sc.nextLine());
                    ArrayList<TouristSpotDto> list = touristSpotController.selectTouristSpotById(memberId, touristSpotId);
                    //detailPage(touristSpotId,list);
                    break;
                case 9: // 메인 화면으로
                    return;
                default:
                    System.out.println("올바른 값을 입력하세요");
                    break;

            }
        }
    }

    // 내 즐겨찾기 리스트
    public void displayBookMarkList(ArrayList<TouristSpotDto> list) {
        for (TouristSpotDto touristSpotDto : list) {
            System.out.println("관광지 ID\t: " + touristSpotDto.getTourist_spot_id());
            System.out.println("제목\t\t\t: " + touristSpotDto.getTitle());
            System.out.println("권역\t\t\t: " + touristSpotDto.getDistrict());
            System.out.println("설명\t\t\t: " + touristSpotDto.getDescription());
            System.out.println("주소\t\t\t: " + touristSpotDto.getAddress());
            System.out.println("전화번호\t\t: " + touristSpotDto.getPhone());
            System.out.println("-------------------------------------");
        }
    }

    public void displayMessage(String msg) {
        System.out.println(msg);
    }


    public void displayLikesCount(Long touristSpotId, int totalLikes) {
        System.out.println("관광지 ID [" + touristSpotId + "]의 현재 좋아요 수: " + totalLikes);
    }

    public void displayBookmarkCountByMember(Long memberId, int totalBookmarks) {


        System.out.println("⭐ [회원" + memberId + "]" + "의 현재 즐겨찾기 수: " + totalBookmarks);
    }

    public void displayTouristSpotsByLikes(List<TouristSpotDto> spots) {
        System.out.println("\n------ 관광지 목록 (좋아요 순) ------");
        for (TouristSpotDto touristSpotDto : spots) {
            System.out.println("관광지 ID\t: " + touristSpotDto.getTourist_spot_id());
            System.out.println("제목\t\t\t: " + touristSpotDto.getTitle());
            System.out.println("권역\t\t\t: " + touristSpotDto.getDistrict());
            System.out.println("설명\t\t\t: " + touristSpotDto.getDescription());
            System.out.println("주소\t\t\t: " + touristSpotDto.getAddress());
            System.out.println("전화번호\t\t: " + touristSpotDto.getPhone());
            System.out.println("좋아요 수\t\t: " + touristSpotDto.getLikeCount());
            System.out.println("-------------------------------------");
        }
    }

    public void displayMyBookMarks(List<TouristSpotDto> favorites) {
        System.out.println("\n------ 내가 즐겨찾기한 관광지 목록 ------");
        if (favorites == null || favorites.isEmpty()) {
            System.out.println("즐겨찾기한 관광지가 없습니다.");
        } else {
            for (TouristSpotDto touristSpotDto : favorites) {
                System.out.println("관광지 ID\t: " + touristSpotDto.getTourist_spot_id());
                System.out.println("제목\t\t\t: " + touristSpotDto.getTitle());
                System.out.println("권역\t\t\t: " + touristSpotDto.getDistrict());
                System.out.println("설명\t\t\t: " + touristSpotDto.getDescription());
                System.out.println("주소\t\t\t: " + touristSpotDto.getAddress());
                System.out.println("전화번호\t\t: " + touristSpotDto.getPhone());
                System.out.println("-------------------------------------");
            }
        }
    }

    public void isAdmin(MemberDto memberDto) {
        if (memberDto.getEmail().startsWith("admin")) {
            Session.setIsAdminTrue();
        }

    }
}
