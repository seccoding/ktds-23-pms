<style>
  .modal-window,
  .modal-confirm-window {
    position: absolute;
    border-radius: 20px;
    border: 0;
    box-shadow: 0px 8px 15px 0px rgba(0, 0, 0, 0.1);
    transition: box-shadow 0.3s ease;

    justify-content: center;
    top: 30%;
    left: 30%;

    width: 460px;
    height: 300px;
    padding: 1rem;

    background-color: rgba(F, F, F, 0.8);
  }
  .modal-close,
  .modal-confirm-close {
    text-align: right;
    color: gray;
  }
  .modal-close:hover,
  .modal-confirm-close:hover {
    color: black;
  }
  .grid-modal,
  .grid-confirm-modal {
    display: grid;
    grid-template-rows: 1fr 6fr 1fr;
  }
  .modal-content,
  .modal-confirm-content {
    align-items: center;
    display: flex;
    justify-content: space-around;
    vertical-align: middle;
    overflow-y: auto;
    max-height: 11rem;
  }
  .modal-text,
  .modal-confirm-text {
    text-align: center;
    color: #b00;
    font-size: 1.2rem;
    font-weight: bold;
  }
  .input-space,
  .input-confirm-space {
    text-align: right;
    align-items: end;
  }
</style>

<dialog class="modal-window">
  <div class="grid-modal">
    <div class="modal-close">X</div>
    <div class="modal-content">
      <div class="modal-text"></div>
    </div>
    <div class="input-space">
      <button class="confirm-button button"></button>
    </div>
  </div>
</dialog>

<dialog class="modal-confirm-window">
  <div class="grid-confirm-modal">
    <div class="modal-confirm-close">X</div>
    <div class="modal-confirm-content">
      <div class="modal-confirm-text"></div>
    </div>
    <div class="input-confirm-space">
      <button class="confirm-confirm-button button"></button>
      <button class="cancel-confirm-button button"></button>
    </div>
  </div>
</dialog>

<!-- JS 내부에 추가해야 할 내용 text안에 본인이 필요한 값을 적어주시면 됩니다.
    그리고 밑의 <<<$(".modal-confirm-close").on("click", function () {
  location.reload();>>> 부분은 x를 누를시 꺼지는 기능입니다. 모달을 켜고 싶은 버튼의 클래스에 부여해 주세요.
 <<$(".test-button").on("click", function ()>> -->

<!-- $(".test-button").on("click", function () {
        var alertModal = $(".modal-confirm-window");
        var modalButton = $(".confirm-confirm-button");
        var modalButton1 = $(".cancel-confirm-button");
        var modalText = $(".modal-confirm-text");
        modalText.text("프로젝트를 삭제하시겠습니까?");
        modalButton.text("확인");
        modalButton1.text("취소");
        alertModal[0].showModal();
    });
$(".modal-confirm-close").on("click", function () {
  location.reload();
}); -->
