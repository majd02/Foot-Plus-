<?php

namespace App\Controller;

use App\Entity\Subs;
use App\Form\SubsType;
use App\Repository\SubsRepository;
use App\Services\QrcodeService;
use Endroid\QrCode\Builder\BuilderInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;


#[Route('/subs')]
class SubsController extends AbstractController
{
    #[Route('/', name: 'subs_index', methods: ['GET'])]
    public function index(SubsRepository $subsRepository): Response
    {
        return $this->render('subs/index.html.twig', [
            'subs' => $subsRepository->findAll(),
        ]);
    }
    #[Route('/', name: 'subs_indexF', methods: ['GET'])]
    public function indexF(SubsRepository $subsRepository): Response
    {
        return $this->render('subs/index.html.twig', [
            'subs' => $subsRepository->findAll(),
        ]);
    }

    #[Route('/new', name: 'subs_new', methods: ['GET', 'POST'])]
    public function new(Request $request): Response
    {
        $sub = new Subs();
        $form = $this->createForm(SubsType::class, $sub);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $file = $form->get('photo')->getData();
            $fileName=md5(uniqid()).'.'.$file->guessExtension();
            $user=$form->getData();
            $user->setPhoto($fileName);

            $this->addFlash('info','a new sub has been created');

            $entityManager->persist($sub);
            $entityManager->flush();

            return $this->redirectToRoute('subs_index');
        }

        return $this->render('subs/new.html.twig', [
            'sub' => $sub,
            'form' => $form->createView(),
        ]);
    }

    #[Route('/newF', name: 'subs_newF', methods: ['GET', 'POST'])]
public function newF(Request $request, QrcodeService $qrcodeService): Response
{

    $qrCode = null;
    $sub = new Subs();
    $form = $this->createForm(SubsType::class, $sub);
    $form->handleRequest($request);

    if ($form->isSubmitted() && $form->isValid()) {
        $entityManager = $this->getDoctrine()->getManager();
        $file = $form->get('photo')->getData();
        $fileName=md5(uniqid()).'.'.$file->guessExtension();
        $user=$form->getData();
        $user->setPhoto($fileName);

        $this->addFlash('info','a new sub has been created');
        $entityManager->persist($sub);
        $entityManager->flush();
        $qrCode = $qrcodeService->qrcode($sub->getNumSub());


        return $this->redirectToRoute('subs_indexF');
    }

    return $this->render('subs/newF.html.twig', [
        'sub' => $sub,
        'form' => $form->createView(),
        'qrCode' => $qrCode

    ]);
}


    #[Route('/{numSub}', name: 'subs_show', methods: ['GET'])]
    public function show(Subs $sub): Response
    {
        return $this->render('subs/show.html.twig', [
            'sub' => $sub,

        ]);
    }

    #[Route('/{numSub}/edit', name: 'subs_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Subs $sub): Response
    {
        $form = $this->createForm(SubsType::class, $sub);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('subs_index');
        }

        return $this->render('subs/edit.html.twig', [
            'sub' => $sub,
            'form' => $form->createView(),
        ]);
    }

    #[Route('/{numSub}', name: 'subs_delete', methods: ['POST'])]
    public function delete(Request $request, Subs $sub): Response
    {
        if ($this->isCsrfTokenValid('delete'.$sub->getNumSub(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($sub);
            $entityManager->flush();
        }

        return $this->redirectToRoute('subs_index');
    }









}
