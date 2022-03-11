<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 리뷰// -->
                    <section id="p_proReview" class="tab-contents proreview">
                        <h3 class="hide"><strong>리뷰</strong></h3>
                        
                        <div class="list-top">
                            <button type="button" class="btn black btn-review" onclick="fn.addClass('#p_proReviewWrite');fn.removeClass('#p_proReview');">리뷰 작성</button>
                            
                            <span class="grade-star big">
                                <span><span style="width:90%;"></span></span>
                                <strong><em>4.5</em> <i class="bar">/</i> 5</strong>
                            </span>
                        </div>

                        <div class="list-sort">
                            <label><input type="checkbox" name="pic" id="onlyPic" data-pic-yn="N" onclick="fnReviewAjaxPcList('A', 1);"><span>포토 리뷰</span></label>
                            <div class="form-sort" id="reviewImgYn">
                                <label><input type="radio" name="sort" value = '1' onclick="fnReviewAjaxPcList('A', 1);"><span>베스트순</span></label>
                                <label><input type="radio" name="sort" value = '2' checked onclick="fnReviewAjaxPcList('A', 1);"><span>최신순</span></label>
                            </div>
                        </div>
                        <div class="review-list">
                        </div>
                    </section>
                    <!-- //리뷰 -->

                    <section id="p_proReviewWrite" class="tab-contents prowrite review">
                        <h3>리뷰작성</h3>
                        <div class="product-list vertical">
                            <span class="thumb">
                                <img src="" alt="" id="popReviewWriteImg">
                            </span>
                            <!-- data-no: 글번호, data-cd:패키지일 경우 상품코드,  data-ord-no: 주문번호-->
                            <div class="contr" data-no="" data-cd="" data-ord-no="">
                                <strong class="txt-ti"></strong>
                                <span class="txt-option"></span>
                            </div>
                        </div>

                        <form id="fileForm" method="post" enctype="multipart/form-data">
                            <fieldset>
                                <legend class="hide">리뷰작성</legend>
                                <input type="hidden" name="itemEvalAtclNo" value="">
                                <input type="hidden" name="ordNo" value="">
                                <input type="hidden" name="slitmCd" value="">
                                <input type="hidden" name="optItemCd" value="">
                                <input type="hidden" name="pckgItemYn" value="">    
                                <input type="hidden" name="itemEvalScrg" id="itemEvalScrg" value="">
                                <input type="hidden" name="itemEvalCntn" id="itemEvalCntn" value="">
                                <input type="hidden" name="imgCnt" id="imgCnt" value="">    
                                <input type="hidden" name="imgYn1" id="imgYn1" value="">            
                                <input type="hidden" name="imgYn2" id="imgYn2" value="">            
                                <input type="hidden" name="imgYn3" id="imgYn3" value="">    
                                <input type="hidden" name="pathType1" id="pathType1" value="">  
                                <input type="hidden" name="pathType2" id="pathType2" value="">
                                <input type="hidden" name="atflNo" id="atflNo" value="">
                                
                                <div class="write-area">
                                    <div class="reviewstar">
                                        <div class="grade-star big active">
                                            <span class="active" id="start1">1점</span>
                                            <span class="active" id="start2">2점</span>
                                            <span class="active" id="start3">3점</span>
                                            <span class="active" id="start4">4점</span>
                                            <span class="active" id="start5">5점</span>
                                            <input type="hidden" name="starV" value="5">
                                            <div class="txt" id='totstart'><em class="tot">5</em>/<em>5</em></div>
                                        </div>
                                    </div>
                                    
                                    <div class="form-default horizon form-file" id="expsY2">
                                        <strong>사진 첨부</strong>
                                        <div class="upload-file">
                                            <label><input type="file" class="upload-hidden" title="사진 첨부" name="uploadImg" id="uploadImg1" accept="image/*"></label>
                                        </div>
                                        <div class="upload-file">
                                            <label><input type="file" class="upload-hidden" title="사진 첨부" name="uploadImg" id="uploadImg2" accept="image/*"></label>
                                        </div>
                                        <div class="upload-file">
                                            <label><input type="file" class="upload-hidden" title="사진 첨부" name="uploadImg" id="uploadImg3" accept="image/*"></label>
                                        </div>
                                    </div>
                                    
                                    <label class="form-counter" id="expsY1">
                                        <textarea title="리뷰 입력" placeholder="최소 글자수는 10자 이상입니다. 고객님의 취향과 경험을 좀 더 자세히 공유해주세요." name="reviewTextarea" id="reviewTextarea" onKeyup="javascript:fnReviewTextareaLimit(this, 500, 'B');"></textarea>
                                        <span class="counter"><em>0</em> /500 자</span>
                                    </label>
                                    <p class="txt-star" id="expsN1">
                                                                개인별 주관적인 의견으로 인해 상품의 기능 및 효과에 대한
                                                                오해의 소지가 있을 수 있어 평점만 선택 가능합니다.
                                    </p>                                
                                </div>

                                <div class="btns">
                                    <button type="button" class="btn gray middle btn-cancel" onclick="fn.addClass('#p_proReview');fn.removeClass('#p_proReviewWrite');">취소</button>
                                    <button type="button" class="btn fill black middle btn-confirm" onclick="fnReviewSave();">확인</button>
                                </div>
                            </fieldset>
                        </form>

                        <div class="infotxt">
                            <strong>리뷰 작성 안내</strong>
                            <ul>
                                <li id="expsY3">권한<br>현대식품관에서 구입한 상품만 가능하며, 배송 완료일 기준 90일까지 작성하실 수 있습니다.</li>
                                <li id="expsY4">혜택<br>리뷰 작성 혜택은 H.Point로 지급되며 H.Point 통합회원이 아닌 경우 지급받으실 수 없습니다.</li>
                                <li id="expsY5">텍스트 리뷰 50P / 포토 리뷰 150P</li>
                                <li id="expsY6">리뷰<br>아래 내용에 해당하는 사유라고  판단되는 경우 작성자 동의 없이 미전시할 수 있으며, 지급된 포인트는 회수됩니다.</li>
                                <li id="expsY7">부적합한 내용의 작성(허위 사실, 욕설, 비난, 일반식품의 효능, 효과, 해석 불가능한 리뷰, 타 상품에 대한 리뷰 등) 타인의  권리 혹은 개인정보 침해 우려가 있는 경우 (캡처. 제3자 사진 도용 등) 리뷰 작성 후 반품</li>
                                
                                <li id="expsN2">리뷰 작성 후 반품 시 지급된 리뷰 포인트는 회수됩니다.</li>
                                <li id="expsN3">포인트는 H.Point로 지급되며 H.Point가 없으신 경우 지급받을 수 없습니다.</li>
                            </ul>
                        </div>
                    </section>
                    <!-- //리뷰작성 -->