package com.multi.travelapp.view;


import com.multi.travelapp.controller.*;
import com.multi.travelapp.model.dto.MemberDto;
import com.multi.travelapp.model.dto.ReviewDto;
import com.multi.travelapp.model.dto.TouristSpotDto;
import com.multi.travelapp.model.dto.SignInDto;

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


    public void firstPage() {
//        while (true) {
//            System.out.println();
//            System.out.println("---로그인/회원가입 화면---");
//            System.out.println("1. 로그인");
//            System.out.println("2. 회원가입");
//            System.out.println("9. Travel App 종료");
//            System.out.print("입력 : ");
//            int input = Integer.parseInt(sc.nextLine());
//            switch (input) {
//                case 1:
//                    signInPage(); // 로그인 화면
//                    break;
//                case 2:
//                    signUpPage(); // 회원가입 화면
//                    break;
//                case 9:
//                    exit(0);
//                    break;
//                default:
//                    System.out.println("올바른 값을 입력해주세요");
//                    break;
//            }
//        }

    }

    public void signInPage() {
        while(true) {
            System.out.println();
            System.out.println("---로그인 화면---");
            System.out.println("1. 로그인");
            System.out.println("9. 로그인/회원가입 화면으로 이동");
            System.out.print("입력 : ");
            int input = Integer.parseInt(sc.nextLine());

            switch (input){
                case 1:
                    System.out.print("이메일 입력 : "); // 이메일 입력
                    String email = sc.nextLine();

                    System.out.print("비밀번호 입력 : "); // 비밀번호 입력
                    String password = sc.nextLine();

                    SignInDto signInDto = new SignInDto(email, password);
                    memberController.signIn(signInDto); // 로그인
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
        while(true) {
            System.out.println();
            System.out.println("---회원가입 화면---");
            System.out.println("1. 회원가입");
            System.out.println("9. 로그인/회원가입 화면으로 이동");
            System.out.print("입력 : ");
            String input = sc.nextLine();

            switch (input){
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
                    if(result){
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



    public void customerMainPage(Long memberId) {
        while(true) {
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
                    touristSpotPage(memberId); // 관광지 목록 조회 화면
                    break;
                case 2:
                    myReviewPage(memberId); // 내 리뷰 화면
                    break;
                case 3:
                    //myBookMarkPage(member); // 즐겨찾기 화면
                    break;
                case 9:
                    return; // 로그아웃 - 로그인 화면으로 이동
                default:
                    System.out.println("올바른 값을 입력하세요");
                    break;
            }
        }
    }


    // ------------------------------- 관광지 서비스 ------------------------------
    // 관광지 조회 방법 선택 화면
    public void touristSpotPage(Long memberId) {
        while(true){
            System.out.println();
            System.out.println("---관광지 조회 방법 선택 화면---");
            System.out.println("1. 관광지 전체 조회(페이지별)");
            System.out.println("2. 관광지 제목으로 조회");
            System.out.println("3. 권역별 관광지 조회");
            System.out.println("4. 좋아요 순으로 조회");
            System.out.println("9. 메인 화면으로 이동");
            System.out.print("입력 : ");
            int input = Integer.parseInt(sc.nextLine());

            switch (input){
                case 1:
                    allTouristSpotPage(memberId); // 관광지 전체 조회(페이지별)
                    break;
                case 2:
                    byTitleTouristSpotPage(memberId); // 관광지 제목으로 조회
                    break;
                case 3:
                    byRegionTouristSpotPage(memberId); // 권역별 관광지 조회
                    break;
                case 4:
                    byLikesTouristSpotPage(memberId); // 좋아요 순으로 조회
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
    public void allTouristSpotPage(Long memberId){
        while(true) {
            System.out.println();
            System.out.println("---관광지 전체 조회(페이지별) 화면---");
            //travelController.selectAllTouristSpot(memberId); // 모든 관광지 조회

            System.out.println("1. 상세 정보 보기");
            System.out.println("9. 관광지 조회 방법 선택 화면으로 이동");
            System.out.print("입력 : ");
            int input = Integer.parseInt(sc.nextLine());

            switch (input){
                case 1:
                    System.out.print("(상세 정보 보기) 관광지 ID 입력 : ");
                    Long touristSpotId = Long.parseLong(sc.nextLine()); // 사용자가 touristSpotId 입력
                    detailPage(memberId, touristSpotId);

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
    public void byTitleTouristSpotPage(Long memberId){
        while(true){
            System.out.println();
            System.out.println("---관광지 제목으로 조회 화면---");
            System.out.println("1. 관광지 제목으로 조회");
            System.out.println("2. 상세 정보 보기");
            System.out.println("9. 관광지 조회 방법 선택 화면으로 이동");
            System.out.print("입력 : ");
            int input = Integer.parseInt(sc.nextLine());

            switch (input){
                case 1:
                    System.out.print("관광지 제목 입력 : ");

                    String title = sc.nextLine();
                    touristSpotController.selectTouristSpotByTitle(memberId, title);
                    break;
                case 2:
                    System.out.println("(상세 정보 보기) 관광지 ID 입력 : ");
                    Long touristSpotId = Long.parseLong(sc.nextLine());
                    detailPage(memberId, touristSpotId);
                    break;
                case 9:
                    return;
                default:
                    System.out.println("올바른 값을 입력하세요");
                    break;
            }

        }
    }

    // 소도시 목록 출력
//    private void showSmallRegionList() {
//        ArrayList<String> smallRegions = TouristSpotController.getSmallRegionList();
//
//        if (smallRegions == null || smallRegions.isEmpty()) {
//            System.out.println("조회된 결과가 없습니다.");
//            return;
//        }
//
//        System.out.println("\n==== 소도시 목록 ====");
//        for (String region : smallRegions) {
//            System.out.print(region + " ");
//        }
//        System.out.println("\n===================");
//    }

    // 권역별 관광지 조회
    public void byRegionTouristSpotPage(Long memberId){
        while(true){
            System.out.println();
            System.out.println("---권역별 관광지 조회 화면---");
            System.out.println("1. 권역별 관광지 조회");
            System.out.println("2. 상세 정보 보기");
            System.out.println("9. 관광지 조회 방법 선택 화면으로 이동");
            System.out.print("입력 : ");
            int input = Integer.parseInt(sc.nextLine());

            switch (input){
                case 1:
                    System.out.print("관광지 권역 입력 : ");
                    String district = sc.nextLine();
                    touristSpotController.selectTouristSpotByDistrict(memberId, district);
                    break;
                case 2:
                    System.out.println("(상세 정보 보기) 관광지 ID 입력 : ");
                    Long touristSpotId = Long.parseLong(sc.nextLine());
                    detailPage(memberId, touristSpotId);
                    break;
                case 9:
                    return;
                default:
                    System.out.println("올바른 값을 입력하세요");
                    break;
            }
        }
    }

    // 좋아요 순으로 조회
    public void byLikesTouristSpotPage(Long memberId){
        while(true){
            System.out.println();
            System.out.println("---좋아요 순으로 조회 화면---");
            System.out.println("1. 좋아요 순으로 관광지 조회");
            System.out.println("2. 상세 정보 보기");
            System.out.println("9. 관광지 조회 방법 선택 화면으로 이동");
            System.out.print("입력 : ");
            int input = Integer.parseInt(sc.nextLine());

            switch (input){
                case 1:
                    //travelController.selectAllTouristSpotOrderByLikes(memberId);
                    break;
                case 2:
                    System.out.print("(상세 정보 보기) 관광지 ID 입력 : ");
                    Long touristSpotId = Long.parseLong(sc.nextLine());
                    detailPage(memberId, touristSpotId);
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
    public void detailPage(Long memberId, Long touristSpotId) {
        while(true){
            System.out.println();
            System.out.println("--- 상세 정보 보기 화면---");
            //travelController.selectTouristSpotById(memberId, touristSpotId); // 관광지 정보 출력
            //travelController.selectAllReviewByTouristSpotId(memberId, touristSpotId); // 리뷰 출력

            System.out.println("1. 리뷰 등록");
            System.out.println("2. 리뷰 삭제");
            System.out.println("3. 즐겨찾기 등록/삭제");
            System.out.println("4. 좋아요 등록/삭제");
            System.out.println("9. 이전 화면으로");
            System.out.print("입력 : ");
            String input = sc.nextLine();

            switch (input){
                case "1": // 리뷰 등록
                    System.out.print("리뷰 제목 입력 : ");
                    String title = sc.nextLine();
                    System.out.print("리뷰 내용 입력 : ");
                    String description = sc.nextLine();
                    System.out.print("리뷰 점수 입력(0~5) : ");
                    int score = Integer.parseInt(sc.nextLine());

                    ReviewDto reviewDto = new ReviewDto();
//                    reviewDto.setTitle(title);
//                    reviewDto.setDescription(description);
//                    reviewDto.setScore(score);
//                    reviewDto.setMemberId(memberId);
//                    reviewDto.setTouristSpotId(touristSpotId);
//
//                    travelController.insertReview(memberId, reviewDto);
                    break;
                case "2": // 리뷰 삭제
                    System.out.print("삭제할 리뷰의 ID를 입력하세요 : ");
                    Long reviewId = Long.parseLong(sc.nextLine());
                    //travelController.deleteReview(memberId, reviewId);
                    break;
                case "3": // 즐겨찾기 등록/삭제
                    System.out.print("즐겨찾기 등록/삭제할 관광지의 ID를 입력하세요 : ");
                    touristSpotId = Long.parseLong(sc.nextLine());
                    //travelController.updateBookMark(memberId, touristSpotId);
                    break;
                case "4": // 좋아요 등록/삭제
                    System.out.print("좋아요 등록/삭제할 관광지의 ID를 입력하세요 : ");
                    touristSpotId = Long.parseLong(sc.nextLine());
                    //travelController.updateLikes(memberId, touristSpotId);
                    break;
                case "9": // 이전 화면으로
                    return;
                default:
                    System.out.println("올바른 값을 입력하세요");
                    break;
            }
        }
    }

    // 관광지 정보 출력
    public void displayTouristSpotList(ArrayList<TouristSpotDto> list) {
        for (TouristSpotDto touristSpotDto : list) {
            System.out.println(touristSpotDto);
        }
    }


    // ------------------------------- 리뷰 서비스 ------------------------------
    // 내 리뷰 화면
    public void myReviewPage(Long memberId){
        while(true) {
            System.out.println();
            System.out.println("---내 리뷰 화면---");
            System.out.println("---내 리뷰 목록---");
            //travelController.selectAllReviewByMemberId(memberId);

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
                    //travelController.deleteReview(memberId, reviewId);
                    break;
                case 2: // 리뷰 수정
                    System.out.print("수정할 리뷰의 ID 입력 : ");
                    reviewId = Long.parseLong(sc.nextLine());
                    updateReviewPage(memberId, reviewId);
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
    public void updateReviewPage(Long memberId, Long reviewId){
        while(true){
            System.out.println();
            System.out.println("---" + reviewId + "번 리뷰 수정 화면---");
            System.out.println("1. 리뷰 수정");
            System.out.println("9. 내 리뷰 화면으로 이동");
            System.out.print("입력 : ");
            int input = Integer.parseInt(sc.nextLine());

            switch (input){
                case 1:
                    System.out.print("수정할 제목 입력 : ");
                    String title = sc.nextLine();
                    System.out.print("수정할 내용 입력 : ");
                    String description = sc.nextLine();
                    System.out.print("수정할 별점 입력(0~5) : ");
                    int score = Integer.parseInt(sc.nextLine());

                    //travelController.updateReview(memberId, reviewId, title, description, score);
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
            System.out.println(reviewDto);
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

        for (TouristSpotDto m : list) {

            System.out.println(m.getTitle());
        }
    }

    public void displayRegionTitle(ArrayList<TouristSpotDto> list) {
        System.out.println("\n===== 관광지 상세 정보 =====");
        for (TouristSpotDto m : list) {

            System.out.println(m);
        }

    }
}
