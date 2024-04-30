$().ready(function () {
        var modalStatus;

        // 전체체크 로직
        $("#checked-all").on("change", function () {
            // 영향을 받을 다른 체크박스를 조회한다.
            var targetClass = $(this).data("target-class");

            // checked-all의 체크 상태를 가져온다.
            // 체크가 되어있다면 true, 아니라면 false
            var isChecked = $(this).prop("checked");

            $("." + targetClass).prop("checked", isChecked);
        });

        var alertModal = $("#alert-modal");
        var modalButton = $("#modal-button");
        var modalText = $(".modal-text");

        var projectId = $("#project").data("project-id");
        var selectedEmployeeId;
        var selectedRole;
        var selectEmployeeListElement;
        var selectRoleListElement;

        // 모달 내 취소 버튼의 로직
        // 이전 모달의 무엇이 사라져야하는지를 작성해야함.
        // 또한 alertModal.hide() 기능이 반드시 포함되어야 한다.
        setCancelButton(function () {
            alertModal.hide();
            alertModal.removeAttr('data-teammateId');
            alertModal.removeAttr('data-teammateIds');

            if ($("#select-teammate")) {
                $("#select-teammate").remove();
            }
        });

        // 삭제 및 등록 로직이 실제 실행되는 모달의 버튼
        // 모달 버튼을 눌렀을 때, 실행될 액션
        // 변수 선언 안하고 바로 function 집어넣어도 됨
        setModalButtonClickAction(function () {
            var teammateId = alertModal.data('teammateId');
            var teammateIds = alertModal.data('teammateIds'); // 이 줄을 추가

            if (teammateId && modalStatus === 'deleteOne') {
                var teammateId = alertModal.data('teammateId');
                $.get("/ajax/teammate/delete/" + teammateId, function (response) {
                    var oneDeleteResult = response.data.result;
                    if (oneDeleteResult) {
                        location.reload();
                    }
                });

                alertModal.hide();
            } else if (teammateIds && teammateIds.length > 0 && modalStatus === 'deleteMassive') {
                // 모달에서 저장된 팀원들의 ID들을 가져옴
                teammateIds = alertModal.data('teammateIds');

                // 서버로 삭제 요청을 보내는 로직
                $.post("/ajax/teammate/delete/massive", {deleteItems: teammateIds}, function (response) {
                    var deleteMassiveResult = response.data.result;
                    if (deleteMassiveResult) {
                        location.reload(); // 페이지 새로고침
                    }
                });
            } else {
                if (!selectedEmployeeId) {
                    return;
                } else {
                    if (modalStatus === 'addTeammate') {
                        alertModal.hide();
                        $.post("/ajax/teammate/add",
                            {
                                prjId: projectId,
                                tmId: selectedEmployeeId,
                                role: selectedRole,
                            }, function (response) {
                                var result = response.data.result;
                                var message = response.data.message;
                                if (result === true) {
                                    location.reload();
                                } else {
                                    modalStatus = 'fail';

                                    modalText.text(message);
                                    modalButton.text("확인")
                                    alertModal.show();
                                }
                            });
                    }
                }
            }

            alertModal.hide();
        });

        // 단일 삭제 시 모달을 띄우는 로직
        $("button[name='deleteTeammate']").on("click", function () {
            modalStatus = 'deleteOne';

            if ($("#select-teammate")) {
                $("#select-teammate").remove();
            }
            alertModal.data('teammateId', $(this).val());
            modalText.text("팀원을 삭제하시겠습니까?");
            modalButton.text("삭제")
            alertModal.show();
        })

        // 체크된 아이템 삭제 모달을 띄우는 로직
        $("#delete-massive-teammate").on("click", function () {
            modalStatus = 'deleteMassive';

            if ($("#select-teammate")) {
                $("#select-teammate").remove();
            }

            var checkedItems = $(".target-teammate-id:checked");

            if (checkedItems.length === 0) {
                modalText.text('삭제할 팀원을 선택해주세요.');
                modalButton.text('확인');
                setModalButtonClickAction(function () {
                    location.reload();
                })
                alertModal.show();
                return;
            }

            // 선택된 체크박스의 ID들을 배열로 추출
            var itemsArray = checkedItems.map(function () {
                return $(this).val();
            }).get();

            // 모달에 선택된 팀원들의 ID들을 저장
            alertModal.data('teammateIds', itemsArray);
            modalText.text("선택한 팀원들을 삭제하시겠습니까?");
            modalButton.text("삭제")
            alertModal.show();
        });

        // 팀원 등록 버튼 클릭 시 모달을 띄우는 로직
        $("#new-teammate").on("click", function () {
            modalStatus = 'addTeammate';

            var deptId = $("#new-teammate").data('dept-id');
            modalText.text("팀원");
            modalButton.text("등록")

            $.get("/ajax/department-teammate/" + deptId, function (response) {
                console.log(response)
                selectRoleListElement = $('<select></select>', {id: 'select-role', name: 'role'});
                selectRoleListElement.append($('<option></option>').val('').text('역할').attr('disabled', true).attr('selected', true).attr('hidden', true));
                selectRoleListElement.append($('<option></option>').val('PL').text('PL'));
                selectRoleListElement.append($('<option></option>').val('NONE').text('NONE'));

                var teammateList = response.data.teammateList;
                selectEmployeeListElement = $('<select></select>', {id: 'select-teammate', name: 'teammate'});

                // "팀원 선택"이라는 기본 옵션 추가
                selectEmployeeListElement.append($('<option></option>').val('').text('팀원을 선택하세요').attr('disabled', true).attr('selected', true).attr('hidden', true));

                teammateList.forEach(function (teammate) {
                    selectEmployeeListElement.append($('<option></option>').val(teammate.empId).text(teammate.empName + "-" + teammate.departmentVO.deptName));
                });

                $('.modal-text').append(selectRoleListElement);
                $('.modal-text').append(selectEmployeeListElement);

                $('#select-role').on('change', function () {
                    selectedRole = $(this).val();
                });

                $('#select-teammate').on('change', function () {
                    selectedEmployeeId = $(this).val();
                });
            })

            alertModal.show();
        })
    }
)